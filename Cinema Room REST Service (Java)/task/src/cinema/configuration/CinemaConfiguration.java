package cinema.configuration;

import cinema.model.Cinema;
import cinema.model.Seat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CinemaConfiguration {
    @Bean
    public Cinema cinema(@Value("${cinema.rows}") int totalRows,
                         @Value("${cinema.cols}") int totalColumns) {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= totalRows; row++) {
            for (int column = 1; column <= totalColumns; column++) {
                Seat seat = new Seat(row, column, calculatePrice(row));
                seats.add(seat); // Initially, all seats are available
            }
        }
        return new Cinema(totalRows, totalColumns, seats);
    }

    private int calculatePrice(int row) {
        return row <= 4 ? 10 : 8;
    }
}
