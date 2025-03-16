package h09.animals;

import h09.abilities.Walks;

/**
 * An object of a class {@link Lion} represents a lion which can walk.
 */
public class Lion extends Animal implements Walks {
    /**
     * Constructs a Lion with the given name and age.
     *
     * @param name the name of the animal
     * @param age  the age of the animal
     */
    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    public int getNumberOfLegs() {
        return 4;
    }
}
