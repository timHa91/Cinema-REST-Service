package cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StatsResponse(@JsonProperty("current_income") int currentIncome,@JsonProperty("number_of_available_seats") int numberOfAvailableSeats,@JsonProperty("number_of_purchased_tickets") int numberOfPurchasedTickets) {
}
