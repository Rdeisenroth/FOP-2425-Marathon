package h08;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.time.LocalDate;

/**
 * Represents a passenger. A Passenger represents an individual with personal details and a unique identifier.
 */
public class Passenger {

    /**
     * The ID of the passenger.
     */
    private String passengerID;

    /**
     * The first name of the passenger.
     */
    private String firstName;

    /**
     * The last name of the passenger.
     */
    private String lastName;

    /**
     * The date of birth of the passenger.
     */
    private LocalDate dateOfBirth;

    /**
     * Constructs a new passenger with the specified first name, last name and date of birth.
     *
     * @param firstName   the first name of the passenger
     * @param lastName    the last name of the passenger
     * @param dateOfBirth the date of birth of the passenger
     */
    public Passenger(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passengerID = generatePassengerID(firstName, lastName, dateOfBirth);

    }

    /**
     * Returns the generated ID of the passenger.
     *
     * @param firstName   the first name of the passenger
     * @param lastName    the last name of the passenger
     * @param dateOfBirth the date of birth of the passenger
     * @return the generated ID of the passenger
     */
    @StudentImplementationRequired("H8.1")
    private String generatePassengerID(String firstName, String lastName, LocalDate dateOfBirth) {
        //TODO H8.1
        return org.tudalgo.algoutils.student.Student.crash("H8.1 - Remove if implemented");
    }

    /**
     * Returns the ID of the passenger.
     *
     * @return the ID of the passenger
     */
    public String getPassengerID() {
        return passengerID;
    }

    /**
     * Returns the first name of the passenger.
     *
     * @return the first name of the passenger
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the passenger.
     *
     * @return the last name of the passenger
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the date of birth of the passenger.
     *
     * @return the date of birth of the passenger
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns a string representation of the passenger.
     *
     * @return a string representation of the passenger
     */
    @Override
    public String toString() {
        return "Passenger{" +
                "passengerID='" + passengerID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
