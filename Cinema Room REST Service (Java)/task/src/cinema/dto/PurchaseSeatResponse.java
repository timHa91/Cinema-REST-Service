package cinema.dto;

public record PurchaseSeatResponse(String token, Ticket ticket) {
    public record Ticket(int row, int column, int price) {
    }
}
