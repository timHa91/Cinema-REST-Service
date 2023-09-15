package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    public ResponseEntity<Map<String, String>> handleTicketAlreadyPurchasedException(TicketAlreadyPurchasedException ex) {
        Map<String, String> errorResponse = Map.of("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(RowOrColumnOutOfBoundsException.class)
    public ResponseEntity<Map<String, String>> rowOrColumnOutOfBoundsException(RowOrColumnOutOfBoundsException ex) {
        Map<String, String> errorResponse = Map.of("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Map<String, String>> tokenExpiredException(TokenExpiredException ex) {
        Map<String, String> errorResponse = Map.of("error", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> authenticationException(AuthenticationException ex) {
        Map<String, String> errorResponse = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
