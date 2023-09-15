package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class Cinema {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns;
    private List<Seat> seats;
}

