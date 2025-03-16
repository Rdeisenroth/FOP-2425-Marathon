package h09.animals;

import h09.abilities.Flies;
import h09.abilities.Swims;

public class FlyingFish extends Animal implements Flies, Swims {
    private boolean flying = false;
    private float elevation = Swims.MIN_ELEVATION;

    /**
     * Constructs an Animal with the given name and age.
     *
     * @param name the name of the animal
     * @param age  the age of the animal
     */
    public FlyingFish(String name, int age) {
        super(name, age);
    }

    @Override
    public void land() {
        flying = false;
        System.out.println(getName() + " landed with a splash!");

    }

    @Override
    public void takeOff() {
        flying = true;
        System.out.println(getName() + " took off and feels the wind in its face!");
    }

    @Override
    public boolean isFlying() {
        System.out.println(getName() + " enjoys flying!");
        return flying;
    }


    @Override
    public float getElevation() {
        return elevation;
    }

    @Override
    public void swimUp() {
        elevation += 2;
        if (elevation > Swims.MAX_ELEVATION) {
            elevation = Swims.MAX_ELEVATION;
            if (!flying) takeOff();
        }
    }

    @Override
    public void swimDown() {
        if (flying) {
            System.out.println(getName() + " can't swim down while flying!");
            return;
        }
        elevation -= 2;
        if (elevation < Swims.MIN_ELEVATION) elevation = Swims.MIN_ELEVATION;
    }
}
