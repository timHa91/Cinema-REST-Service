package cinema.dto;

import java.util.List;

public record CinemaDTO(int total_rows,
                            int total_columns,
                            List<SeatDTO> available_seats) {
}
