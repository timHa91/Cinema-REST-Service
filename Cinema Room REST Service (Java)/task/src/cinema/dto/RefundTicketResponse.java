package cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RefundTicketResponse(@JsonProperty("returned_ticket") ReturnedTicket returnedTicket) {
    public record ReturnedTicket(int row, int column, int price) {
    }
}
