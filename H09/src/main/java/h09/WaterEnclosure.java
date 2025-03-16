package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * An object of a class implementing {@link WaterEnclosure} has the ability to contain and manage a stack of
 * {@link Animal}s which have the ability to {@link Swims}.
 */
// TODO: H9.2.2
public class WaterEnclosure<A extends Animal & Swims> implements Enclosure<A> {
    /**
     * The stack of animals which is used manage the contained Animals.
     */
    @StudentImplementationRequired("H9.2.2") // TODO: H9.2.2
    final StackOfObjects<A> animals = new StackOfObjects<>();

    @StudentImplementationRequired("H9.2.2")
    // @Override
    public StackOfObjects<A> getStack() {
        // TODO: H9.2.2
        return animals;
    }

    @StudentImplementationRequired("H9.2.2")
    // @Override
    public void feed() {
        // TODO: H9.2.2
        for (int i = 0; i < animals.size(); i++) {
            var animal = animals.get(i);
            if (animal.isHungry()) {
                if (animal.getElevation() < Swims.HIGH_ELEVATION) {
                    animal.swimUp();
                }
                animal.eat();
                animal.swimDown();
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
        for (int i = 0; i < animals.size(); i++) {
            var animal = animals.get(i);
            sum += animal.getElevation();
        }
        return sum / animals.size();
    }
}
