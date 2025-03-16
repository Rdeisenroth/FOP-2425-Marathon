package h08;

import java.util.Arrays;

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
     * @param airportCode the airport code where the flight should be managed
     * @param flight      the flight to be added or removed
     * @param isAddOperation if true, the flight will be added; if false, the flight will be removed
     */
    public void manageFlight(String airportCode, Flight flight, boolean isAddOperation) {
        //TODO H8.5.2
        org.tudalgo.algoutils.student.Student.crash("H8.5.2 - Remove if implemented");
    }

    /**
     * Returns a flight from a specific airport.
     *
     * @param airportCode  the airport code from which the flight should be returned
     * @param flightNumber the flight number of the flight
     * @return a flight from a specific airport
     */
    public Flight getFlight(String airportCode, String flightNumber){
        //TODO H8.5.1
        return org.tudalgo.algoutils.student.Student.crash("H8.5.1 - Remove if implemented");
    }

    /**
     * Returns a flight with a specified flight number.
     *
     * @param flightNumber the flight number of the flight
     * @return a flight with a specified flight number
     */
    public Flight getFlight(String flightNumber){
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
    private Airport searchAirport(String airportCode) {
        //TODO H8.5.1
        return org.tudalgo.algoutils.student.Student.crash("H8.5.1 - Remove if implemented");
    }

    /**
     * Searches for a flight in departing or arriving flights.
     *
     * @param airport      the airport in which the flight should be searched
     * @param flightNumber the flight number of the flight
     * @return a flight in departing or arriving flights
     */
    private Flight searchFlight(Airport airport, String flightNumber) {
        //TODO H8.5.1
        return org.tudalgo.algoutils.student.Student.crash("H8.5.1 - Remove if implemented");
    }
}
