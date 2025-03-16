package h10;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Iterator;
import java.util.List;

import static org.tudalgo.algoutils.student.Student.crash;

public class ListItemExamples {

    /**
     * Creates a doubly linked list of CardGamePlayer objects from an array of names.
     * Each name in the array is used to create a CardGamePlayer object.
     * The list is doubly linked, with each ListItem pointing to the next and previous ListItem.
     *
     * @param names an array of names to create CardGamePlayer objects
     * @return the head of the doubly linked list of CardGamePlayer objects
     */
    @StudentImplementationRequired("H10.1.1")
    public static ListItem<CardGamePlayer> createPlayerListFromNames(String[] names) {
        return crash(); // TODO: H10.1.1
    }

    /**
     * Counts the number of SKIP cards in a doubly linked list of PlayingCard objects iteratively.
     *
     * @param cardDeck the head of the doubly linked list of PlayingCard objects
     * @return the number of SKIP cards in the list
     */
    @StudentImplementationRequired("H10.1.2")
    public static int countSkipCardsIterative(ListItem<PlayingCard> cardDeck) {
        return crash(); // TODO: H10.1.2
    }

    /**
     * Counts the number of SKIP cards in a doubly linked list of PlayingCard objects recursively.
     *
     * @param cardDeck the head of the doubly linked list of PlayingCard objects
     * @return the number of SKIP cards in the list
     */
    @StudentImplementationRequired("H10.1.3")
    public static int countSkipCardsRecursive(ListItem<PlayingCard> cardDeck) {
        return crash(); // TODO: H10.1.3
    }

    /**
     * Counts the number of SKIP cards in a list of PlayingCard objects using an iterator.
     *
     * @param cardDeck the list of PlayingCard objects
     * @return the number of SKIP cards in the list
     */
    @StudentImplementationRequired("H10.1.4")
    @SuppressWarnings({"WhileLoopReplaceableByForEach", "ForLoopReplaceableByForEach"})
    public static int countSkipCardsIterator(List<PlayingCard> cardDeck) {
        return crash(); // TODO: H10.1.4
    }

}
