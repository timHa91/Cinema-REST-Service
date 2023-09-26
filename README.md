# Cinema Room REST Service

The Cinema Room REST Service is a Java-based RESTful web service that simulates a cinema booking system. The application allows users to view available seats, purchase tickets, return tickets, and view the statistics of the cinema room.

## Key Functionalities

### View Available Seats

The application returns a map of the cinema room with each seat represented by a coordinate. Seats are either available or purchased.

### Purchase Tickets

Users can purchase a ticket for an available seat. When a ticket is purchased, the seat becomes unavailable for others. The response includes a ticket object with the ticket data.

### Return Tickets

Users can return their purchased tickets. When a ticket is returned, the seat becomes available for others.

### View Statistics

Users can view the statistics of the cinema room. The statistics include the current income, the number of purchased tickets, and the total capacity of the cinema room.

## Setup

1. Clone the repository.
2. Build the project by running `./gradlew build`.
3. Run the application by running `./gradlew run`.

## Project Structure

- `src/main/java/cinema`: Contains the main application files.
- `src/main/java/cinema/model`: Contains the data models for the application.
- `src/main/java/cinema/controller`: Contains the controllers for handling HTTP requests.
- `src/main/java/cinema/service`: Contains the service classes that contain the business logic of the application.
- `src/main/java/cinema/exception`: Contains custom exception classes for the application.

## Key Dependencies

- `Spring Boot`: Provides the framework for creating stand-alone, production-grade Spring based Applications.
- `Spring Web`: Allows the application to include both Spring MVC and Spring WebFlux.
- `Spring Boot DevTools`: Provides fast application restarts, LiveReload, and configurations for enhanced development experience.

---
