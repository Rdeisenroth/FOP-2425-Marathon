package h08;

import h08.rubric.TaskRubricProvider;
import h08.rubric.task.AtomicTask;
import h08.rubric.task.CompositeTask;
import h08.rubric.task.Subtask;
import h08.rubric.task.Task;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;

import java.util.List;
import java.util.Map;

/**
 * Provides the rubrics for H08.
 *
 * @author Nhan Huynh
 */
public class H08_RubricProvider extends TaskRubricProvider {

    /**
     * Defines the rubric for task H08.1.
     */
    private static final Task H08_1 = AtomicTask.builder()
        .description("H08.1 | Have your ID ready")
        .testClassName("h08.H08_1_Tests")
        .criterion("Die Methode generatePassengerID stellt sicher, dass die ersten zwei Zeichen der ID die Initialen des Vornamens und Nachnamens sind.", "testGeneratePassengerIDNameInitials", JsonParameterSet.class)
        .criterion("Die Methode generatePassengerID stellt sicher, dass der Hash-Code des Datums korrekt im zweiten Teil der ID enthalten ist.", "testGeneratePassengerIDDateHash", JsonParameterSet.class)
        .build();

    /**
     * Defines the rubric for subtask H08.2.1
     */
    private static final Subtask H08_2_1 = Subtask.builder()
        .description("H8.2.1 | Let’s get in shape.")
        .testClassName("h08.H08_2_1_Tests")
        .criterion("Die Methode validateFlightNumber überprüft die Flugnummer korrekt.", Map.of(
            "testValidateFlightNumber", List.of(),
            "testValidateFlightNumberException", List.of(JsonParameterSet.class)
        ))
        .criterion("Der Konstruktor der Klasse Flight enthält assert-Anweisungen, die die Eingaben überprüfen.", Map.of(
            "testFlightConstructor", List.of(),
            "testFlightConstructorException", List.of(JsonParameterSet.class)
        ))
        .build();

    /**
     * Defines the rubric for subtask H08.2.2
     */
    private static final Subtask H08_2_2 = Subtask.builder()
        .description("H08.2.2 | Fasten your seatbelts")
        .testClassName("h08.H08_2_2_Tests")
        .criterion("Die Methode bookSeat() reserviert korrekt Sitzplätze.", "testBookSeat", JsonParameterSet.class)
        .criterion("Die Methode wirft korrekt eine NoSeatsAvailableException, wenn keine Plätze mehr verfügbar sind.", Map.of(
            "testNoSeatsAvailableException", List.of(),
            "testBookSeatException", List.of()
        ))
        .build();

    /**
     * Defines the rubric for task H08.2.
     */
    private static final Task H08_2 = CompositeTask.builder()
        .description("H08.2 | Flight Zone")
        .subtasks(H08_2_1, H08_2_2)
        .build();

    /**
     * Defines the rubric for task H08.3.
     */
    private static final Task H08_3 = AtomicTask.builder()
        .description("H08.3 | Exception Handling")
        .testClassName("h08.H08_3_Tests")
        .criterion("Die FlightManagementException ist korrekt implementiert.", "testFlightManagementException")
        .criterion("Die BookingManagementException ist korrekt implementiert.", "testBookingManagementException")
        .criterion("Die FlightNotFoundException, BookingNotFoundException und InvalidBookingException sind korrekt.", Map.of(
            "testFlightNotFoundException", List.of(),
            "testBookingNotFoundException", List.of(),
            "testInvalidBookingException", List.of())
        )
        .criterion("Die BookingAlreadyCancelledException und DuplicateBookingException sind korrekt.", Map.of(
            "testBookingAlreadyCancelledException", List.of(),
            "testDuplicateBookingException", List.of())
        ).build();

    /**
     * Defines the rubric for subtask H08.4.1.
     */
    private static final Subtask H08_4_1 = Subtask.builder()
        .description("H08.4.1 | Adding a Flight")
        .testClassName("h08.H08_4_1_Tests")
        .criterion("Die Methode addFlight fügt Flüge korrekt zu abgehenden oder ankommenden Flügen hinzu.", "testAddFlight", JsonParameterSet.class)
        .criterion("Die Methode prüft und behandelt korrekt falsche Flughafencodes.", "testAddFlightException", JsonParameterSet.class)
        .build();

    /**
     * Defines the rubric for subtask H08.4.2.
     */
    private static final Subtask H08_4_2 = Subtask.builder()
        .description("H08.4.2 | Removing a Flight")
        .testClassName("h08.H08_4_2_Tests")
        .criterion("Die Methode removeFlight entfernt Flüge korrekt aus den Listen.", "testRemoveFlight", JsonParameterSet.class)
        .criterion("Die Methode wirft korrekt eine FlightNotFoundException, wenn der Flug nicht gefunden wird.", "testRemoveFlightException", JsonParameterSet.class)
        .build();

    /**
     * Defines the rubric for subtask H08.4.3.
     */
    private static final Subtask H08_4_3 = Subtask.builder()
        .description("H08.4.3 | Getting a Flight")
        .testClassName("h08.H08_4_3_Tests")
        .criterion("Die Methode getFlight gibt Flüge korrekt zurück.", "testGetFlight", JsonParameterSet.class)
        .criterion("Die Methode wirft korrekt eine FlightNotFoundException, wenn der Flug nicht existiert.", "testGetFlightException", JsonParameterSet.class)
        .build();

    /**
     * Defines the rubric for subtask H08.4.4.
     */
    private static final Subtask H08_4_4 = Subtask.builder()
        .description("H08.4.4 | Removing a booking")
        .testClassName("h08.H08_4_4_Tests")
        .criterion("Die Methode cancelBooking() storniert eine Buchung korrekt.", "testCancelBooking", JsonParameterSet.class)
        .criterion("Die Methode wirft korrekt eine BookingAlreadyCancelledException, wenn die Buchung bereits storniert wurde.", "testCancelBookingException", JsonParameterSet.class)
        .build();

    /**
     * Defines the rubric for task H08.4.
     */
    private static final Task H08_4 = CompositeTask.builder()
        .description("H08.4 | Airport Command Center")
        .subtasks(H08_4_1, H08_4_2, H08_4_3, H08_4_4)
        .build();

    /**
     * Defines the rubric for subtask H08.5.1.
     */
    private static final Subtask H08_5_1 = Subtask.builder()
        .description("H8.5.1 | Airport and Flight Lookup")
        .testClassName("h08.H08_5_1_Tests")
        .criterion("Die Methode searchAirport findet Flughäfen korrekt.", Map.of(
            "testSearchAirport", List.of(JsonParameterSet.class),
            "testSearchAirportException", List.of(JsonParameterSet.class)
        ))
        .criterion("Die Methode searchFlight durchsucht Flüge korrekt.", "testSearchFlight", JsonParameterSet.class)
        .criterion("Die Methode getFlight gibt Flüge korrekt zurück.", Map.of(
            "testGetFlight", List.of(JsonParameterSet.class),
            "testGetFlightNull", List.of(JsonParameterSet.class)
        ))
        .build();

    /**
     * Defines the rubric for subtask H08.5.2.
     */
    private static final Subtask H08_5_2 = Subtask.builder()
        .description("H08.5.2 | Flight and Booking Management")
        .testClassName("h08.H08_5_2_Tests")
        .criterion("Die Methode manageFlight verwaltet Flüge korrekt (Hinzufügen oder Entfernen).", "testManageFlight", JsonParameterSet.class)
        .criterion("Die Methode manageFlight prüft korrekt die Flughafencodes.", "testManageFlightCode")
        .criterion("Die Methode validateAndCheckBooking validiert Buchungsdetails korrekt.", "validateAndCheckBookingInvalid", JsonParameterSet.class)
        .criterion("Die Methode validateAndCheckBooking prüft korrekt auf doppelte Buchungen.", "validateAndCheckBookingDuplicate", JsonParameterSet.class)
        .build();

    /**
     * Defines the rubric for subtask H08.5.3.
     */
    private static final Subtask H08_5_3 = Subtask.builder()
        .description("H8.5.3 | Searching a Booking")
        .testClassName("h08.H08_5_3_Tests")
        .criterion("Die Methode searchBooking durchsucht Buchungen korrekt.", Map.of(
            "testSearchBooking", List.of(JsonParameterSet.class),
            "testSearchBookingException", List.of(JsonParameterSet.class)
        ))
        .criterion("Die Methode getBooking gibt Buchungen korrekt zurück.", Map.of(
            "testGetBooking", List.of(JsonParameterSet.class),
            "testGetBookingException", List.of(JsonParameterSet.class)
        ))
        .build();

    /**
     * Defines the rubric for subtask H08.5.4.
     */
    private static final Subtask H08_5_4 = Subtask.builder()
        .description("H08.5.4 | Cancelling a Booking")
        .testClassName("h08.H08_5_4_Tests")
        .criterion("Die Methode cancelBooking storniert Buchungen korrekt.", "testCancelBooking", JsonParameterSet.class)
        .criterion("Die Methode gibt die richtigen Fehlermeldungen oder Bestätigungen aus.", Map.of(
            "testCancelBookingMessage", List.of(JsonParameterSet.class),
            "testCancelBookingAlreadyCancelled", List.of(JsonParameterSet.class),
            "testCancelBookingNotFound", List.of(JsonParameterSet.class)
        ))
        .build();

    /**
     * Defines the rubric for subtask H08.5.5.
     */
    private static final Subtask H08_5_5 = Subtask.builder()
        .description("H08.5.5 | Creating a Booking")
        .testClassName("h08.H08_5_5_Tests")
        .criterion("Die Methode createBooking erstellt Buchungen korrekt.", "testCreateBooking", JsonParameterSet.class)
        .criterion("Die Methode behandelt alle relevanten Ausnahmen korrekt.", Map.of(
            "testCreateBookingDuplicate", List.of(JsonParameterSet.class),
            "testCreateBookingInvalid", List.of(JsonParameterSet.class),
            "testCreateBookingNoSeats", List.of(JsonParameterSet.class)
        ))
        .criterion("Die Methode validiert Buchungsdetails und reserviert Sitzplätze korrekt.", "testCreateBookingValidation", JsonParameterSet.class)
        .build();

    /**
     * Defines the rubric for task H08.5.
     */
    private static final Task H08_5 = CompositeTask.builder()
        .description("H08.5 | Booking and Flight Management")
        .subtasks(H08_5_1, H08_5_2, H08_5_3, H08_5_4, H08_5_5)
        .build();

    /**
     * Creates a new rubric provider for H08.
     */
    public H08_RubricProvider() {
        super(8, "Flight Control: Navigating the Exceptions", true);
    }

    @Override
    public List<Task> getTasks() {
        return List.of(H08_1, H08_2, H08_3, H08_4, H08_5);
    }
}
