package h01;

import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;
import h01.template.Util;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * The {@link RedGhost} is a {@link Robot} that looks like a red ghost. It tries to move in the direction of the chased
 * robot.
 */
public class RedGhost extends Robot implements Ghost, TickBased {
    private final Robot chased;

    /**
     * Creates a new {@link RedGhost} at the given position.
     *
     * @param x      the x-coordinate
     * @param y      the y-coordinate
     * @param chased the robot that is being chased
     */
    public RedGhost(int x, int y, Robot chased) {
        super(x, y, Families.GHOST_RED);
        this.chased = chased;
    }

    /**
     * Moves the robot in the direction of the chased robot.
     */
    @Override
    @StudentImplementationRequired("H2.4")
    public void doMove() {
        // get pacman dir

        while (getDirection() != Util.furthestDirection(chased, this)) {
            turnLeft();
        }
        while (!isFrontClear()) {
            turnLeft();
        }
        move();
    }
}
