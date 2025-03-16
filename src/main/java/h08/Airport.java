package h08;


import java.util.Arrays;

/**
 * Represents an airport. An airport manages departing and arriving flights, allowing for their addition, removal, and retrieval based on the airport code.
 */
public class Airport {

    /**
     * The code of the airport.
     */
    private String airportCode;

    /**
     * The departing flights of the airport.
     */
    private Flight[] departingFlights;

    /**
     * The arriving flights to the airport.
     */
    private Flight[] arrivingFlights;

    /**
     * The number of departing flights of the airport.
     */
    private int departingSize;

    /**
     * The number of arriving flights to the airport.
     */
    private int arrivingSize;

    /**
     * Constructs a new airport with the specified airport code and initial capacity.
     *
     * @param airportCode     the code of the airport
     * @param initialCapacity the initial capacity of the airport
     */
    public Airport(String airportCode, int initialCapacity) {
        this.airportCode = airportCode;
        this.departingFlights = new Flight[initialCapacity];
        this.arrivingFlights = new Flight[initialCapacity];
        this.departingSize = 0;
        this.arrivingSize = 0;
    }

    /**
     * Adds a flight.
     *
     * @param flight      the flight to add
     * @param isDeparting if true, adds to departing flights, otherwise to arriving flights
     * @throws IllegalArgumentException if the flight's airport code doesn't match the airport's code
     */
    public void addFlight(Flight flight, boolean isDeparting) {
        //TODO H8.4.1
        org.tudalgo.algoutils.student.Student.crash("H8.4.1 - Remove if implemented");
    }

    /**
     * Removes a flight by flight number.
     *
     * @param flightNumber the flight number
     * @param isDeparting  if true, removes from departing flights, otherwise from arriving flights
     * @throws FlightNotFoundException if the flight is not found
     */
    public void removeFlight(String flightNumber, boolean isDeparting) {
        //TODO H8.4.2
        org.tudalgo.algoutils.student.Student.crash("H8.4.2 - Remove if implemented");
    }

    /**
     * Returns a flight by flight number.
     *
     * @param flightNumber the flight number
     * @param isDeparting  if true, searches in departing flights, otherwise in arriving flights
     * @return the flight with the specified flight number
     * @throws FlightNotFoundException if the flight is not found
     */
    public Flight getFlight(String flightNumber, boolean isDeparting) {
        //TODO H8.4.3
        return org.tudalgo.algoutils.student.Student.crash("H8.4.3 - Remove if implemented");
    }


    /**
     * Returns the departing flights of the airport.
     *
     * @return the departing flights of the airport
     */
    public Flight[] getAllDepartingFlights() {
        return Arrays.copyOf(departingFlights, departingSize);
    }

    /**
     * Returns the arriving flights to the airport.
     *
     * @return the arriving flights to the airport
     */
    public Flight[] getAllArrivingFlights() {
        return Arrays.copyOf(arrivingFlights, arrivingSize);
    }

    /**
     * Returns the airport code.
     *
     * @return the airport code
     */
    public String getAirportCode() {
        return airportCode;
    }
}
