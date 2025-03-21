package h08;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import h08.Exceptions.NoSeatsAvailableException;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Represents a flight. A flight offers information such as the flight details and seat management.
 */
public class Flight {

    /**
     * The flight number of the flight.
     */
    private String flightNumber;

    /**
     * The departure airport of the flight.
     */
    private String departure;

    /**
     * The destination airport of the flight.
     */
    private String destination;

    /**
     * The departure time of the flight.
     */
    private LocalDateTime departureTime;

    /**
     * The initial number of seats of the flight.
     */
    private final int initialSeats;

    /**
     * The number of available seats.
     */
    private int availableSeats;

    /**
     * Constructs a new flight with the specified flight number, departure airport, destination airport, departure time
     * and initial number of seats.
     *
     * @param flightNumber  the flight number of the flight
     * @param departure     the departure airport of the flight
     * @param destination   the destination airport of the flight
     * @param departureTime the departure time of the flight
     * @param initialSeats  the initial number of seats of the flight
     */
    public Flight(String flightNumber, String departure, String destination, LocalDateTime departureTime, int initialSeats) {
        //TODO H8.2.1
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.initialSeats = initialSeats;
        this.availableSeats = initialSeats;

        Stream.of(departure, destination, departureTime, initialSeats)
            .forEach(x -> {
                assert (x != null);
            });
        assert (initialSeats >= 0);
        validateFlightNumber(flightNumber);
    }

    /**
     * Validates the flight number.
     *
     * @param flightNumber the flight number of the flight
     */
    @StudentImplementationRequired("H8.2.1")
    public void validateFlightNumber(String flightNumber) {
        //TODO H8.2.1
        assert (flightNumber.matches("[A-Z]{2}\\d{3,4}"));
    }

    /**
     * Returns the flight number of the flight.
     *
     * @return the flight number of the flight
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns the departure airport of the flight.
     *
     * @return the departure airport of the flight
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * Returns the destination airport of the flight.
     *
     * @return the destination airport of the flight
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns the departure time of the flight.
     *
     * @return the departure time of the flight
     */
    public String getDepartureTime() {
        return departureTime.toString();
    }

    /**
     * Returns the number of available seats of the flight.
     *
     * @return the number of available seats of the flight
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    @StudentImplementationRequired("H8.2.2")
    public void bookSeat() throws NoSeatsAvailableException {
        if (availableSeats <= 0) {
            throw new NoSeatsAvailableException(flightNumber);
        }
        availableSeats--;
    }

    public void cancelSeat() {
        if (availableSeats < initialSeats) {
            availableSeats++;
        }
    }

    /**
     * Returns a string representation of the flight.
     *
     * @return a string representation of the flight
     */
    @Override
    public String toString() {
        return "Flight{" +
            "flightNumber='" + flightNumber + '\'' +
            ", departure='" + departure + '\'' +
            ", destination='" + destination + '\'' +
            ", departureTime=" + departureTime +
            ", initialSeats=" + initialSeats +
            ", availableSeats=" + availableSeats +
            '}';
    }
}
