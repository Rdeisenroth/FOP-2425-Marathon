package h08;

/**
 * Represents a booking management. A booking management oversees booking operations, ensuring validity and handling duplicates.
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
        //TODO H8.5.5
        org.tudalgo.algoutils.student.Student.crash("H8.5.5 - Remove if implemented");
    }

    /**
     * Validates the booking details and checks for duplicates.
     *
     * @param bookingId    the booking ID of the booking
     * @param flightNumber the flight number of the booking
     * @param passengerId  the passenger ID of the booking
     * @throws InvalidBookingException if the booking details are invalid
     * @throws DuplicateBookingException if the booking ID is already in use
     */
    private void validateAndCheckBooking(String bookingId, String flightNumber, String passengerId){
        //TODO H8.5.2
        org.tudalgo.algoutils.student.Student.crash("H8.5.2 - Remove if implemented");
    }

    /**
     * Searches for a booking by booking ID.
     *
     * @param bookingId the booking ID of the booking
     * @return the booking with the specified booking ID
     * @throws BookingNotFoundException if the booking ist not found
     */
    private Booking searchBooking(String bookingId){
        //TODO H8.5.3
        return org.tudalgo.algoutils.student.Student.crash("H8.5.3 - Remove if implemented");
    }

    /**
     * Returns a booking by booking ID.
     *
     * @param bookingId the booking ID of the booking
     * @return the booking with the specified booking ID
     */
    public Booking getBooking(String bookingId) {
        //TODO H8.5.3
        return org.tudalgo.algoutils.student.Student.crash("H8.5.3 - Remove if implemented");
    }

    /**
     * Cancels a booking by booking ID.
     *
     * @param bookingId the booking ID of the booking
     */
    public void cancelBooking(String bookingId) {
        //TODO H8.5.4
        org.tudalgo.algoutils.student.Student.crash("H8.5.4 - Remove if implemented");
    }
}
