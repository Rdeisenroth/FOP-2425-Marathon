package h08;


import java.util.Arrays;
import java.util.stream.IntStream;

import h08.Exceptions.FlightNotFoundException;

/**
 * Represents an airport. An airport manages departing and arriving flights, allowing for their addition, removal, and
 * retrieval based on the airport code.
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
        if (isDeparting) {
            if (!flight.getDeparture().equals(airportCode)) {
                throw new IllegalArgumentException("Flight's departure airport code does not match this airport's code");
            }
            if (departingSize >= departingFlights.length) {
                departingFlights = Arrays.copyOf(departingFlights, departingFlights.length * 2);
            }
            departingFlights[departingSize++] = flight;
            return;
        }
        if (!flight.getDestination().equals(airportCode)) {
            throw new IllegalArgumentException("Flight's arrival airport code does not match this airport's code");
        }
        if (arrivingSize >= arrivingFlights.length) {
            arrivingFlights = Arrays.copyOf(arrivingFlights, arrivingFlights.length * 2);
        }
        arrivingFlights[arrivingSize++] = flight;
    }

    /**
     * Removes a flight by flight number.
     *
     * @param flightNumber the flight number
     * @param isDeparting  if true, removes from departing flights, otherwise from arriving flights
     * @throws FlightNotFoundException if the flight is not found
     */
    public void removeFlight(String flightNumber, boolean isDeparting) throws FlightNotFoundException {
        if (isDeparting) {
            var pos = IntStream.range(0, departingSize)
                .filter(x -> departingFlights[x].getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElseThrow(() -> new FlightNotFoundException("Departing flight not found: " + flightNumber));
            // set null
            departingFlights[pos] = departingFlights[departingSize - 1];
            departingFlights[departingSize - 1] = null;
            departingSize--;
            return;
        }
        var pos = IntStream.range(0, arrivingSize)
            .filter(x -> arrivingFlights[x].getFlightNumber().equals(flightNumber))
            .findFirst()
            .orElseThrow(() -> new FlightNotFoundException("Arriving flight not found: "+ flightNumber));
        // set null
        arrivingFlights[pos] = arrivingFlights[arrivingSize - 1];
        arrivingFlights[arrivingSize - 1] = null;
        arrivingSize--;
    }

    /**
     * Returns a flight by flight number.
     *
     * @param flightNumber the flight number
     * @param isDeparting  if true, searches in departing flights, otherwise in arriving flights
     * @return the flight with the specified flight number
     * @throws FlightNotFoundException if the flight is not found
     */
    public Flight getFlight(String flightNumber, boolean isDeparting) throws FlightNotFoundException {
        //TODO H8.4.3
        return isDeparting
               ? Arrays.stream(departingFlights).filter(x -> x.getFlightNumber().equals(flightNumber)).findFirst()
                   .orElseThrow(
                       () -> new FlightNotFoundException("Departing flight not found: " + flightNumber)
                   )
               : Arrays.stream(arrivingFlights).filter(x -> x.getFlightNumber().equals(flightNumber)).findFirst()
                   .orElseThrow(
                       () -> new FlightNotFoundException("Arriving flight not found: " + flightNumber)
                   );
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
