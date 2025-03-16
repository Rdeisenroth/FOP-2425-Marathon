package h10;

import org.jetbrains.annotations.Nullable;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.NoSuchElementException;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A doubly linked list implementation.
 *
 * @param <T> the type of elements stored in the list
 * @author Manuel Peters
 */
public class DoublyLinkedList<T> implements MyList<T> {

    /**
     * The head of the doubly linked list. Points to the first element in the list.
     */
    @DoNotTouch
    ListItem<T> head;

    /**
     * The tail of the doubly linked list. Points to the last element in the list.
     */
    @DoNotTouch
    ListItem<T> tail;

    /**
     * The size of the doubly linked list.
     */
    @DoNotTouch
    int size;

    /**
     * Constructs an empty doubly linked list.
     */
    @DoNotTouch
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    @DoNotTouch
    @Override
    public int findFirst(T key) {
        return findFirstHelper(head, key, 0);
    }

    /**
     * Helper method to find the first occurrence of an element in the list recursively.
     *
     * @param p     the current ListItem
     * @param key   the element to be checked for presence in the list
     * @param index the index of the current ListItem
     * @return the index of the element if the element is present, -1 otherwise
     */
    @StudentImplementationRequired("H10.2.1")
    private int findFirstHelper(ListItem<T> p, T key, int index) {
        return crash(); // TODO: H10.2.1
    }

    @DoNotTouch
    @Override
    public int size() {
        return size;
    }

    @DoNotTouch
    @Override
    public T get(int index) {
        return getListItem(index).key;
    }

    /**
     * Retrieves the ListItem at the specified index in the doubly linked list.
     *
     * @param index the index of the ListItem to retrieve
     * @return the ListItem at the specified index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    @StudentImplementationRequired("H10.2.2")
    private ListItem<T> getListItem(int index) {
        return crash(); // TODO: H10.2.2
    }

    @DoNotTouch
    @Override
    public void add(T key) {
        add(size, key);
    }

    @StudentImplementationRequired("H10.2.3")
    @Override
    public void add(int index, @Nullable T key) {
        crash(); // TODO: H10.2.3
    }

    /**
     * Removes the specified ListItem from the doubly linked list.
     *
     * @param p the ListItem to be removed
     * @return the key of the removed ListItem
     */
    @StudentImplementationRequired("H10.2.4")
    private T removeListItem(ListItem<T> p) {
        return crash(); // TODO: H10.2.4
    }

    @DoNotTouch
    @Override
    public T removeAtIndex(int index) {
        ListItem<T> p = getListItem(index);
        return removeListItem(p);
    }

    @StudentImplementationRequired("H10.2.5")
    @Override
    public void clear() {
        crash(); // TODO: H10.2.5
    }

    /**
     * An iterator for traversing a doubly linked list in a cyclic manner.
     */
    class CyclicIterator implements BidirectionalIterator<T> {

        /**
         * The current ListItem of the iterator in the doubly linked list.
         */
        @DoNotTouch
        ListItem<T> p;

        /**
         * Indicates whether the `remove` method has been called after the last call to the `next` or `previous` method.
         */
        @DoNotTouch
        boolean calledRemove;

        /**
         * Constructs a new cyclic iterator.
         */
        @DoNotTouch
        public CyclicIterator() {
            this.p = null;
            this.calledRemove = false;
        }

        /**
         * Returns {@code true} if this iterator has more elements when
         * traversing the list in the forward direction.
         *
         * @return {@code true} if the list iterator has more elements when traversing the list in the forward direction
         */
        @Override
        @StudentImplementationRequired("H10.3.1")
        public boolean hasNext() {
            return crash(); // TODO: H10.3.1
        }

        /**
         * Returns the next element in the iteration and advances the cursor position.
         * This method may be called repeatedly to iterate through the list,
         * or intermixed with calls to {@link #previous} to go back and forth.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if there are no more elements to iterate over
         */
        @Override
        @StudentImplementationRequired("H10.3.1")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The list is empty");
            }

            return crash(); // TODO: H10.3.1
        }

        /**
         * Returns {@code true} if this iterator has more elements when
         * traversing the list in the reverse direction.
         *
         * @return {@code true} if the iteration has more elements in the reverse direction
         */
        @StudentImplementationRequired("H10.3.2")
        public boolean hasPrevious() {
            return crash(); // TODO: H10.3.2
        }

        /**
         * Returns the previous element in the iteration and moves the cursor position backwards.
         * This method may be called repeatedly to iterate through the list,
         * or intermixed with calls to {@link #next} to go back and forth.
         *
         * @return the previous element in the iteration
         * @throws NoSuchElementException if there are no more elements to iterate over
         */
        @StudentImplementationRequired("H10.3.2")
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException("The list is empty");
            }

            return crash(); // TODO: H10.3.2
        }

        /**
         * Removes the last element that was returned.
         *
         * @throws IllegalStateException if the `next` or `previous` method has not been called yet or the element has
         *                               already been removed
         */
        @Override
        @DoNotTouch
        public void remove() {
            if (p == null) {
                throw new IllegalStateException("next or previous method has not been called yet");
            } else if (calledRemove) {
                throw new IllegalStateException("Element has already been removed");
            }

            removeListItem(p);
            calledRemove = true;
        }
    }

    @DoNotTouch
    @Override
    public BidirectionalIterator<T> cyclicIterator() {
        return new CyclicIterator();
    }

    /**
     * Returns a string representation of the doubly linked list.
     * The string representation consists of a list of the elements in the order they are stored,
     * enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).
     *
     * @return a string representation of the list
     */
    @Override
    @DoNotTouch
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (ListItem<T> p = head; p != null; p = p.next) {
            sb.append(p.key);
            if (p.next == null) {
                break;
            }
            sb.append(',').append(' ');
        }
        return sb.append(']').toString();
    }
}
