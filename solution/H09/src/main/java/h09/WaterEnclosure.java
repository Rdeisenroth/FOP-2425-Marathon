package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An object of a class implementing {@link WaterEnclosure} has the ability to contain and manage a stack of
 * {@link Animal}s which have the ability to {@link Swims}.
 */
public class WaterEnclosure<A extends Animal & Swims> implements Enclosure<A> {
    /**
     * The stack of animals which is used manage the contained Animals.
     */
    @StudentImplementationRequired("H9.2.2")
    final StackOfObjects<A> animals = new StackOfObjects<>();

    @StudentImplementationRequired("H9.2.2")
    @Override
    public StackOfObjects<A> getStack() {
        return animals;
    }

    @StudentImplementationRequired("H9.2.2")
    @Override
    public void feed() {
        for (int i = 0; i < this.getStack().size(); i++) {
            A a = this.getStack().get(i);
            if (a.isHungry()) {
                if (a.getElevation() < Swims.HIGH_ELEVATION) a.swimUp();
                a.eat();
                a.swimDown();
            }
        }
    }

    /**
     * Compares the elevations of all {@link Animal}s in the enclosure and returns the mean.
     *
     * @return the mean elevation of all {@link Animal}s in the enclosure
     */
    @StudentImplementationRequired("H9.2.2")
    public float getMeanElevation() {
        float sum = 0;
        for (int i = 0; i < this.getStack().size(); i++) {
            A a = this.getStack().get(i);
            sum += a.getElevation();
        }
        return sum / this.getStack().size();
    }
}
