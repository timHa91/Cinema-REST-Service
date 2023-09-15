package cinema.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Token {
    UUID token;

    public Token() {
        this.token = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return token.toString(); // For the Response, that the token field is a simple string, not nested within a token object
    }
}