package h09.animals;

import h09.abilities.Swims;
import h09.abilities.Walks;

/**
 * An object of a class {@link Penguin} represents a penguin which can swim and walk.
 */
public class Penguin extends Animal implements Walks, Swims {
    float elevation = Swims.MAX_ELEVATION / Swims.MIN_ELEVATION;

    /**
     * Constructs a Penguin with the given name and age.
     *
     * @param name the name of the animal
     * @param age  the age of the animal
     */
    public Penguin(String name, int age) {
        super(name, age);
    }

    /**
     * Constructs a Penguin with the given name, age and elevation.
     *
     * @param name      the name of the animal
     * @param age       the age of the animal
     * @param elevation the elevation of the animal
     */
    public Penguin(String name, int age, float elevation) {
        super(name, age);
        this.elevation = elevation;
        if (elevation > Swims.MAX_ELEVATION) this.elevation = Swims.MAX_ELEVATION;
        if (elevation < Swims.MIN_ELEVATION) this.elevation = Swims.MIN_ELEVATION;
    }

    @Override
    public float getElevation() {
        return elevation;
    }

    @Override
    public void swimUp() {
        elevation = Swims.MAX_ELEVATION;
    }

    @Override
    public void swimDown() {
        elevation -= 4;
        if (elevation < Swims.MIN_ELEVATION) elevation = Swims.MIN_ELEVATION;
    }

    @Override
    public int getNumberOfLegs() {
        return 2;
    }
}
