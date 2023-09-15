package cinema.controller;

import cinema.dto.*;
import cinema.exception.AuthenticationException;
import cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    private final CinemaService cinemaService;
    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }
    @GetMapping("/seats")
    public CinemaDTO getSeats() {
        return cinemaService.getAvailableSeats();
    }
    @PostMapping("/purchase")
    public PurchaseSeatResponse purchaseSeat(@RequestBody PurchaseSeatRequest seatRequest) {
        return cinemaService.purchaseSeat(seatRequest.row(), seatRequest.column());
    }
    @PostMapping("/return")
    public RefundTicketResponse refundTicket(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        return cinemaService.refundTicket(token);
    }
    @GetMapping("/stats")
    public StatsResponse getStats(@RequestParam(value = "password", required = false) String password) {
        if(password != null && "super_secret".equals(password)) {
            return cinemaService.getStats();
        }
        else {
            throw new AuthenticationException("The password is wrong!");
        }
    }
}
