package cinema.exception;

public class TicketAlreadyPurchasedException extends RuntimeException {
    public TicketAlreadyPurchasedException(String msg) {
        super(msg);
    }
}

