package h09.animals;

import h09.abilities.Swims;

/**
 * An object of a class {@link Fish} represents a fish which can swim.
 */
public class Fish extends Animal implements Swims {
    /**
     * Constructs a Fish with the given name and age.
     *
     * @param name the name of the animal
     * @param age  the age of the animal
     */
    public Fish(String name, int age) {
        super(name, age);
    }

    @Override
    public float getElevation() {
        return Swims.MAX_ELEVATION - (Swims.MAX_ELEVATION - Swims.MIN_ELEVATION) / (getName().length() + 1);
    }

    @Override
    public void swimUp() {
        name += "Blub";
    }

    @Override
    public void swimDown() {
        if (name.length() < 4) return;
        name = name.substring(0, name.length() - 4);
    }
}
