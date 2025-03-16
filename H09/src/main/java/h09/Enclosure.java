package h09;

import h09.animals.Animal;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * An object of a class implementing {@link Enclosure} has the ability to contain and manage a stack of {@link Animal}s.
 */
// TODO: H9.2.1
public interface Enclosure<TODO_REPLACE> {
    /**
     * @return the stack of animals which is used manage the contained {@link Animal}s
     */
    @StudentImplementationRequired("H9.2.1")
    // TODO: H9.2.1
    StackOfObjects getStack();

    /**
     * Feeds all contained animals.
     */
    @DoNotTouch
    void feed();

    /**
     * Counts the number of hungry {@link Animal}s in the enclosure.
     *
     * @return number of hungry {@link Animal}s in the enclosure
     */
    @DoNotTouch
    @SuppressWarnings("RedundantCast")
    default int countHungry() {
        int count = 0;
        for (int i = 0; i < this.getStack().size(); i++)
            if (((Animal) this.getStack().get(i)).isHungry()) count++;
        return count;
    }


    /**
     * Applies a {@link Consumer} operation on each {@link Animal} in the enclosure.
     *
     * @param func operation to be applied to each {@link Animal} in the enclosure
     */
    @StudentImplementationRequired("H9.3.1") // TODO: H9.3.1
    default void forEach(Consumer func) {
        for (int i = 0; i < this.getStack().size(); i++)
            func.accept(this.getStack().get(i));
    }

    /**
     * Tests a {@link Predicate} operation on each {@link Animal} in the enclosure and removes every {@link Animal}
     * which does not satisfy the predicate. That means only {@link Animal}s for which the predicate returns 'true'
     * remain in the enclosure.
     *
     * @param filter operation to test to each {@link Animal} in the enclosure
     */
    @StudentImplementationRequired("H9.3.2") // TODO: H9.3.2
    default void filterObj(Predicate filter) {
        for (int i = 0; i < this.getStack().size(); i++) {
            Object a = this.getStack().get(i);
            if (!filter.test(a)) {
                this.getStack().remove(a);
                i--;
            }
        }
    }

    /**
     * Returns a new {@link Enclosure} that contains only the {@link Animal}s of the previous {@link Enclosure} which
     * satisfied the predicate. That means only {@link Animal}s for which the predicate returns 'true' are included
     * in the new enclosure.
     *
     * @param supp   {@link Supplier} which is used to create the new {@link Enclosure} to be returned
     * @param filter operation to test to each {@link Animal} in the enclosure
     * @param <E>    Type of the new {@link Enclosure} which is returned
     * @return a new {@link Enclosure} that contains only the {@link Animal}s of the previous {@link Enclosure} which
     * satisfied the predicate
     */
    @StudentImplementationRequired("H9.3.3")
    default Enclosure filterFunc(Supplier<Enclosure> supp, Predicate<Object> filter) {
        // TODO: H9.3.3
        return org.tudalgo.algoutils.student.Student.crash("H9.3.3 - Remove if implemented");
    }

    /**
     * {@link Predicate} which returns true if an {@link Animal} is old (that means older than 10).
     */
    @DoNotTouch
    Predicate<Animal> IS_OLD = animal -> animal.getAge() > 10;

    /**
     * {@link Predicate} which returns true if a swimming {@link Animal} swims at a low elevation.
     */
    @StudentImplementationRequired("H9.4.1") // TODO: H9.4.1
    Predicate SWIMS_AT_LOW_ELEVATION = null;

    /**
     * {@link Consumer} which lets the consumed {@link Animal} eat and sleep.
     */
    @StudentImplementationRequired("H9.4.2") // TODO: H9.4.2
    Consumer FEED_AND_SLEEP = null;

    /**
     * Returns a {@link Consumer} which lets the consumed swimming {@link Animal} eat and swim down.
     *
     * @param <T> Type of the swimming {@link Animal} to eat and swim down
     * @return a {@link Consumer} which lets the consumed swimming {@link Animal} eat and swim down
     */
    @StudentImplementationRequired("H9.4.3")
    static Consumer EAT_AND_SINK() {
        // TODO: H9.4.3
        return org.tudalgo.algoutils.student.Student.crash("H9.4.3 - Remove if implemented");
    }
}
