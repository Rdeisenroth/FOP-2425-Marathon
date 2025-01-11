package h01;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;

/**
 * {@link OrangeGhost} is a {@link Robot} that looks like a orange ghost.
 * It tries to move in a straight line and alternates between turning left and
 * right.
 */
public class OrangeGhost extends Robot implements Ghost, TickBased {
    /**
     * Creates a new {@link OrangeGhost} at the given position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public OrangeGhost(int x, int y) {
        super(x, y, Families.GHOST_ORANGE);
    }

    private boolean leftTurnNext = false;

    /**
     * Moves the robot in a straight line if possible.
     * If the robot cannot move forward, it turns left or right until there is no
     * wall in front.
     * The robot alternates between turning left and right.
     */
    @Override
    @StudentImplementationRequired("H2.3")
    public void doMove() {
        if (isFrontClear()) {
            move();
            return;
        } else {
            while (!isFrontClear()) {
                if (leftTurnNext) {
                    turnLeft();
                } else {
                    turnLeft();
                    turnLeft();
                    turnLeft();
                }
            }
            leftTurnNext = !leftTurnNext;
        }
    }
}
