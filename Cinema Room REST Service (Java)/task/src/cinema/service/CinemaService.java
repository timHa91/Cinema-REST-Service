package cinema.service;

import cinema.dto.*;
import cinema.exception.RowOrColumnOutOfBoundsException;
import cinema.exception.TicketAlreadyPurchasedException;
import cinema.exception.TokenExpiredException;
import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CinemaService {
    private final Cinema cinema;
    private final Set<PurchaseSeatResponse> purchasedSeats = new HashSet<>();
    @Autowired
    public CinemaService(Cinema cinema, Token token) {
        this.cinema = cinema;
    }
    public CinemaDTO getAvailableSeats() {
        // Get a list of available seats in the cinema
        List<SeatDTO> availableDtoSeats = cinema.getSeats().stream()
                .filter(Seat::isAvailable)
                .map(this::convertSeatToDTO)
                .toList();
        // Returns a CinemaDTO object to represent total rows and columns and the available seats
        return new CinemaDTO(cinema.getTotalRows(),
                cinema.getTotalColumns(),
                availableDtoSeats);
    }

    private SeatDTO convertSeatToDTO(Seat seat) {
        return new SeatDTO(seat.getRow(), seat.getColumn(), seat.getPrice());
    }
    public PurchaseSeatResponse purchaseSeat(int row, int column) {
        // Check if the row and column are within bounds
        if (row < 0 || row >= cinema.getTotalRows() || column < 0 || column >= cinema.getTotalColumns()) {
            throw new RowOrColumnOutOfBoundsException( "The number of a row or a column is out of bounds!");
        }
        // Find the seat that matches the specified row and column
        Seat foundSeat = findSeat(row, column);
        // Check if a seat was found
        if(foundSeat!=null){
            if(!foundSeat.isAvailable()){
                throw new TicketAlreadyPurchasedException( "The ticket has been already purchased!");
            }
            // Mark the seat as not available
            foundSeat.setAvailable(false);
            // Convert the purchased seat to a Purchased Seat
            PurchaseSeatResponse purchasedSeat = convertToPurchaseResponse(foundSeat);
            purchasedSeats.add(purchasedSeat);
            return purchasedSeat;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found.");
    }
    private Seat findSeat(int row, int column) {
        return cinema.getSeats().stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column  )
                .findFirst()
                .orElse(null);
    }
    private PurchaseSeatResponse convertToPurchaseResponse(Seat seat) {
        Token token = new Token();
        return new PurchaseSeatResponse(token.toString(), new PurchaseSeatResponse.Ticket(seat.getRow(), seat.getColumn(), seat.getPrice()));
    }
    public RefundTicketResponse refundTicket(String responseToken) {
        PurchaseSeatResponse matchingSeat = purchasedSeats.stream()
                .filter(purchasedSeat -> purchasedSeat.token().equals(responseToken))
                .findFirst()
                .orElse(null);
        if (matchingSeat != null) {
            purchasedSeats.remove(matchingSeat);
            setSeatAvailable(matchingSeat);
            return new RefundTicketResponse(convertTicketToReturnedTicket(matchingSeat.ticket()));
        } else {
            throw new TokenExpiredException("Wrong token!");
        }
    }
    private void setSeatAvailable(PurchaseSeatResponse purchasedSeat) {
        findSeat(purchasedSeat.ticket().row(), purchasedSeat.ticket().column()).setAvailable(true);
    }
    private RefundTicketResponse.ReturnedTicket convertTicketToReturnedTicket(PurchaseSeatResponse.Ticket ticket) {
        return new RefundTicketResponse.ReturnedTicket(ticket.row(), ticket.column(), ticket.price());
    }

    public StatsResponse getStats() {
        int currentIncome = getCurrentIncome();
        int numberAvailableSeats = getNumberOfAvailableSeats();
        int numberPurchasedSeats = getNumberOfPurchasedSeats();
        return new StatsResponse(currentIncome, numberAvailableSeats, numberPurchasedSeats);
    }
    private int getCurrentIncome() {
        return purchasedSeats.stream()
                .mapToInt(seat -> seat.ticket().price())
                .sum();
    }
    private int getNumberOfAvailableSeats() {
        return cinema.getSeats().stream()
                .filter(Seat::isAvailable)
                .toList()
                .size();
    }
    private int getNumberOfPurchasedSeats() {
        return purchasedSeats.stream()
                .toList()
                .size();
    }
}
