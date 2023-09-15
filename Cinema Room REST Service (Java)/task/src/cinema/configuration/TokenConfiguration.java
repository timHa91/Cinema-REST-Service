package cinema.configuration;

import cinema.model.Token;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class TokenConfiguration {
    @Bean
    public Token createToken() {
        return new Token(UUID.randomUUID());
    }
}
