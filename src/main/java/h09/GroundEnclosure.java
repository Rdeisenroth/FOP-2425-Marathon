package h09;

import h09.abilities.Walks;
import h09.animals.Animal;

/**
 * An object of a class implementing {@link GroundEnclosure} has the ability to contain and manage a stack of
 * {@link Animal}s which have the ability to {@link Walks}.
 */
public class GroundEnclosure<A extends Animal & Walks> implements Enclosure<A> {
    /**
     * The stack of animals which is used manage the contained Animals.
     */
    final StackOfObjects<A> animals = new StackOfObjects<>();

    @Override
    public StackOfObjects<A> getStack() {
        return animals;
    }

    @Override
    public void feed() {
        for (int i = 0; i < this.getStack().size(); i++) {
            A a = this.getStack().get(i);
            a.eat();
        }
    }

    /**
     * Counts the total number of legs of all {@link Animal}s in the enclosure.
     *
     * @return the total number of legs of all {@link Animal}s in the enclosure
     */
    public int countLegs() {
        int sum = 0;
        for (int i = 0; i < this.getStack().size(); i++)
            sum += this.getStack().get(i).getNumberOfLegs();
        return sum;
    }
}
