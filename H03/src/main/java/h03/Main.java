package h03;

import fopbot.World;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // Create a 5x5 world and make it visible
        World.setSize(5, 5);
        World.setVisible(true);
    }
}
