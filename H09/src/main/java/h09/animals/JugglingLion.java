package h09.animals;

public class JugglingLion extends Lion {
    private int numberOfBalls;
    private static final int MAX_BALLS = 7;

    public JugglingLion(String name, int age) {
        super(name, age);
        this.numberOfBalls = 3; // Default number of balls
    }

    /**
     * Prints a message that the lion is juggling.
     */
    public void juggle() {
        System.out.println(getName() + " is juggling " + numberOfBalls + " balls! What a talented lion!");
        if (numberOfBalls == MAX_BALLS) {
            System.out.println("Wow! " + getName() + " is juggling the maximum number of balls!");
        }
        if (numberOfBalls > MAX_BALLS) {
            System.out.println("Oh no! " + getName() + " is dropping the balls!");
            numberOfBalls = 0;
        }
    }

    /**
     * Increases the number of balls the lion is juggling.
     */
    public void increaseBalls() {
        numberOfBalls++;
        System.out.println(getName() + " increased the number of balls to " + numberOfBalls + "!");
    }
}
