package h09.animals;

import h09.abilities.Swims;
import h09.abilities.Walks;

import java.util.Random;

public class Turtle extends Animal implements Walks, Swims {
    private float elevation = Swims.MAX_ELEVATION / Swims.MIN_ELEVATION;
    private final Random random = new Random();

    /**
     * Constructs an Animal with the given name and age.
     *
     * @param name the name of the animal
     * @param age  the age of the animal
     */
    public Turtle(String name, int age) {
        super(name, age);
    }


    @Override
    public float getElevation() {
        return elevation;
    }

    private void setElevation(float elevation) {
        this.elevation = elevation;
        if (elevation > Swims.MAX_ELEVATION) this.elevation = Swims.MAX_ELEVATION;
        if (elevation < Swims.MIN_ELEVATION) this.elevation = Swims.MIN_ELEVATION;
    }

    @Override
    public void swimUp() {
        float dElevation = (random.nextFloat()) * 2 - 0.5f; // Random float between -0.5 and 1.5
        if (dElevation < 0) System.out.println("Oh no! " + getName() + " is sinking!");
        setElevation(elevation + dElevation);
    }

    @Override
    public void swimDown() {
        float dElevation = (random.nextFloat()) * 2 - 0.5f; // Random float between -0.5 and 1.5
        if (dElevation < 0) System.out.println("Oh no! " + getName() + " is floating up!");
        setElevation(elevation - dElevation);
    }

    @Override
    public int getNumberOfLegs() {
        return 4;
    }
}
