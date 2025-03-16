package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An object of a class implementing {@link WaterEnclosure} has the ability to contain and manage a stack of
 * {@link Animal}s which have the ability to {@link Swims}.
 */
// TODO: H9.2.2
public class WaterEnclosure<TODO_REPLACE> {
    /**
     * The stack of animals which is used manage the contained Animals.
     */
    @StudentImplementationRequired("H9.2.2") // TODO: H9.2.2
    final StackOfObjects animals = null;

    @StudentImplementationRequired("H9.2.2")
    // @Override
    public StackOfObjects getStack() {
        // TODO: H9.2.2
        return org.tudalgo.algoutils.student.Student.crash("H9.2.2 - Remove if implemented");
    }

    @StudentImplementationRequired("H9.2.2")
    // @Override
    public void feed() {
        // TODO: H9.2.2
        org.tudalgo.algoutils.student.Student.crash("H9.2.2 - Remove if implemented");
    }

    /**
     * Compares the elevations of all {@link Animal}s in the enclosure and returns the mean.
     *
     * @return the mean elevation of all {@link Animal}s in the enclosure
     */
    @StudentImplementationRequired("H9.2.2")
    public float getMeanElevation() {
        // TODO: H9.2.2
        return org.tudalgo.algoutils.student.Student.crash("H9.2.2 - Remove if implemented");
    }
}
