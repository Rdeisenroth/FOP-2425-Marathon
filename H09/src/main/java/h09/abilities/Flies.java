package h09.abilities;

public interface Flies {
    /**
     * Lets the animal land.
     */
    void land();

    /**
     * Lets the animal fly.
     */
    void takeOff();

    /**
     * @return whether the animal is flying.
     */
    boolean isFlying();
}
