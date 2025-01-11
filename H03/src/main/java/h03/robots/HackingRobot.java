package h03.robots;

import fopbot.Robot;

import java.util.Arrays;
import java.util.Random;

public class HackingRobot extends Robot {
    private MovementType type;
    private MovementType[] robotTypes = new MovementType[]{MovementType.TELEPORT, MovementType.OVERSTEP, MovementType.DIAGONAL};

    public HackingRobot(int x, int y, boolean order) {
        super(x, y);
        System.out.println(order);
        if (!order) { // links
            final var tmp = robotTypes[0];
            for (int i = 1; i < robotTypes.length; i++) {
                robotTypes[i - 1] = robotTypes[i];
            }
            robotTypes[robotTypes.length - 1] = tmp;
        } else { // rechts
            var tmp = robotTypes[robotTypes.length - 1];
            for (int i = robotTypes.length - 2; i >= 0; i--) {

                robotTypes[i + 1] = robotTypes[i];
            }
            robotTypes[0] = tmp;
        }
        type = robotTypes[0];
    }

    public MovementType getType() {
        return type;
    }

    public MovementType getNextType() {
        return robotTypes[(Arrays.asList(robotTypes).indexOf(type) + 1) % robotTypes.length];
    }

    public int getRandom(final int limit) {
        return new Random().nextInt(limit);
    }

    public boolean shuffle(int itNr) {
        var origType = type;
        for (int i = 0; i < itNr; i++) {
            type = robotTypes[getRandom(robotTypes.length)];
        }
        return type != origType;
    }

    public void shuffle() {
        while (!shuffle(1)) {
        } // toll
    }
}
