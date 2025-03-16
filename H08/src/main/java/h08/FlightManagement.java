package h08;

import java.util.Arrays;
import java.util.function.Function;

import h08.Exceptions.FlightNotFoundException;
import org.jetbrains.annotations.Nullable;

import static h08.Utils.tryBool;

/**
 * Represents a flight management. A flight management oversees the management of flights and airports.
 */
public class FlightManagement {

    /**
     * The airports whose flights are managed.
     */
    private Airport[] airports;

    /**
     * The current number of airports whose flights are managed.
     */
    private int size;

    /**
     * Constructs a new flight management with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity
     */
    public FlightManagement(int initialCapacity) {
        this.airports = new Airport[initialCapacity];
        this.size = 0;
    }

    /**
     * Adds an airport to the flight management.
     *
     * @param airport the airport to be added
     */
    public void addAirport(Airport airport) {
        if (size >= airports.length) {
            airports = Arrays.copyOf(airports, airports.length * 2);
        }
        airports[size++] = airport;
    }

    /**
     * Manages the addition or removal of a flight for a specific airport.
     *
     * @param airportCode    the airport code where the flight should be managed
     * @param flight         the flight to be added or removed
     * @param isAddOperation if true, the flight will be added; if false, the flight will be removed
     */
    public void manageFlight(String airportCode, Flight flight, boolean isAddOperation) {
        try {
            final var airport = searchAirport(airportCode);
            if (isAddOperation) {
                tryBool(() -> airport.addFlight(flight, true))
                    .orElseTry(() -> airport.addFlight(flight, false))
                    .orElseThrow(e -> new IllegalArgumentException("Flight's airport code(s) do not match the provided airport code"));
            } else {
                final var removeTry = tryBool(() -> airport.removeFlight(flight.getFlightNumber(), true))
                    .orElseTry(() -> airport.removeFlight(flight.getFlightNumber(), false));
                if (!removeTry.wasSuccessful()) {
                    System.out.println("Error removing flight: " + removeTry.e().getStackTrace());
                }
            }
        } catch (Exception e) {
            System.out.println("Error managing flight: " + e.getStackTrace());
        }
    }

    /**
     * Returns a flight from a specific airport.
     *
     * @param airportCode  the airport code from which the flight should be returned
     * @param flightNumber the flight number of the flight
     * @return a flight from a specific airport
     */
    public @Nullable Flight getFlight(String airportCode, String flightNumber) {
        try {
            var airport = searchAirport(airportCode);
            var flight = searchFlight(airport, flightNumber);
            if (flight == null) {
                throw new FlightNotFoundException("Flight not found: " + flightNumber);
            }
            return flight;
        } catch (Throwable e) {
            System.out.println("Error retrieving flight: " + e.getStackTrace());
        }
        return null;
    }

    /**
     * Returns a flight with a specified flight number.
     *
     * @param flightNumber the flight number of the flight
     * @return a flight with a specified flight number
     */
    public @Nullable Flight getFlight(String flightNumber) {
        for (int i = 0; i < size; i++) {
            Flight flight = searchFlight(airports[i], flightNumber);
            if (flight != null) {
                return flight;
            }
        }
        System.out.println("Error retrieving flight: Flight not found: " + flightNumber);
        return null;
    }

    /**
     * Searches for an airport by airport code.
     *
     * @param airportCode the airport code
     * @return an airport by airport code
     * @throws Exception if the airport ist not found
     */
    private Airport searchAirport(String airportCode) throws Exception {
        //TODO H8.5.1
        return Arrays.stream(airports).filter(x -> x.getAirportCode().equals(airportCode)).findFirst().orElseThrow(() -> new Exception("Airport not found: " + airportCode));
    }

    /**
     * Searches for a flight in departing or arriving flights.
     *
     * @param airport      the airport in which the flight should be searched
     * @param flightNumber the flight number of the flight
     * @return a flight in departing or arriving flights
     */
    private @Nullable Flight searchFlight(Airport airport, String flightNumber) {
        return Arrays.stream(airport.getAllDepartingFlights())
            .filter(x -> x.getFlightNumber().equals(flightNumber))
            .findFirst()
            .orElse(Arrays.stream(airport.getAllArrivingFlights())
                        .filter(x -> x.getFlightNumber().equals(flightNumber))
                        .findFirst()
                        .orElse(null)
            );
    }
}
