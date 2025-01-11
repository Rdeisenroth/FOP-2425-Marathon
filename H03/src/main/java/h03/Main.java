package h03;

import fopbot.World;
import h03.robots.HackingRobot;

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
        new HackingRobot(2,2,false);
        World.setVisible(true);
    }
}
