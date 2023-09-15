package cinema.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean isAvailable;
    public Seat (int row, int column, int price) {
        this.column = column;
        this.row = row;
        this.price = price;
        this.isAvailable = true;
    }
}
