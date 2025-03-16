package h08;

import java.util.Arrays;
import java.util.List;

import h08.Exceptions.BookingAlreadyCancelledException;
import h08.Exceptions.BookingNotFoundException;
import h08.Exceptions.DuplicateBookingException;
import h08.Exceptions.FlightNotFoundException;
import h08.Exceptions.InvalidBookingException;
import h08.Exceptions.NoSeatsAvailableException;

/**
 * Represents a booking management. A booking management oversees booking operations, ensuring validity and handling
 * duplicates.
 */
public class BookingManagement {

    /**
     * The bookings to be managed.
     */
    private Booking[] bookings;

    /**
     * The current number of bookings.
     */
    private int size;

    /**
     * The flight management for the bookings.
     */
    private FlightManagement flightManagement;

    /**
     * Constructs a new booking management with the specified initial capacity and flight management.
     *
     * @param initialCapacity  the initial number of bookings that can be managed
     * @param flightManagement the flight management for the bookings
     */
    public BookingManagement(int initialCapacity, FlightManagement flightManagement) {
        this.bookings = new Booking[initialCapacity];
        this.size = 0;
        this.flightManagement = flightManagement;
    }

    /**
     * Creates a booking.
     *
     * @param bookingId    the booking ID of the booking
     * @param flightNumber the flight number of the booking
     * @param passengerId  the passenger ID of the booking
     */
    public void createBooking(String bookingId, String flightNumber, String passengerId) {
        try {
            validateAndCheckBooking(bookingId, flightNumber, passengerId);
            var flight = flightManagement.getFlight(flightNumber);
            if (flight == null) {
                throw new FlightNotFoundException("Flight not found: " + flightNumber);
            }
            flight.bookSeat();
            if (bookings.length <= size) {
                var tmp = new Booking[2 * bookings.length];
                System.arraycopy(bookings, 0, tmp, 0, bookings.length);
                bookings = tmp;
                bookings[size++] = new Booking(bookingId, flightNumber, passengerId);
            }
        } catch (DuplicateBookingException e) {
            System.out.println("Booking already exists: " + bookingId);
        } catch (InvalidBookingException e) {
            System.out.println("Invalid booking details: " + e.getMessage());
        } catch (NoSeatsAvailableException e) {
            // ignore
        } catch (FlightNotFoundException e) {
            // darf man behandeln, wie man will
            System.out.println("Rosen sind rot, KW macht Krawall, wer auch immer diese HÜ geschrieben hat, der hat nen Knall!");
        }
    }

    /**
     * Validates the booking details and checks for duplicates.
     *
     * @param bookingId    the booking ID of the booking
     * @param flightNumber the flight number of the booking
     * @param passengerId  the passenger ID of the booking
     * @throws InvalidBookingException   if the booking details are invalid
     * @throws DuplicateBookingException if the booking ID is already in use
     */
    private void validateAndCheckBooking(String bookingId, String flightNumber, String passengerId) throws InvalidBookingException {
        for (var s : new String[]{bookingId, flightNumber, passengerId}) {
            if (s == null || s.isEmpty()) {
                throw new InvalidBookingException("Invalid booking details provided.");
            }
        }
        if (Arrays.stream(bookings).anyMatch(x -> x.getBookingId().equals(bookingId))) {
            throw new DuplicateBookingException("A booking with this ID already exists.");
        }
    }

    /**
     * Searches for a booking by booking ID.
     *
     * @param bookingId the booking ID of the booking
     * @return the booking with the specified booking ID
     * @throws BookingNotFoundException if the booking ist not found
     */
    private Booking searchBooking(String bookingId) throws BookingNotFoundException {
        //TODO H8.5.3
        return Arrays.stream(bookings).filter(x -> x.getBookingId().equals(bookingId)).findFirst().orElseThrow(() -> new BookingNotFoundException("Booking not found: " + bookingId));
    }

    /**
     * Returns a booking by booking ID.
     *
     * @param bookingId the booking ID of the booking
     * @return the booking with the specified booking ID
     */
    public Booking getBooking(String bookingId) {
        try {
            return searchBooking(bookingId);
        } catch (BookingNotFoundException e) {
            System.out.println("Error retrieving booking: " + e.getMessage());
            return null;
        }
    }

    /**
     * Cancels a booking by booking ID.
     *
     * @param bookingId the booking ID of the booking
     */
    public void cancelBooking(String bookingId) {
        try {
            var booking = searchBooking(bookingId);
            booking.cancelBooking();
            System.out.println("Booking cancelled successfully: " + bookingId);
        } catch (BookingAlreadyCancelledException e) {
            System.out.println("Already cancelled booking: " + bookingId);
        } catch (BookingNotFoundException e) {
            System.out.println("Error cancelling booking: " + e.getMessage());
        }
    }
}
