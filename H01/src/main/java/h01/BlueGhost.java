package h01;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;

/**
 * The {@link BlueGhost} is a {@link Robot} that looks like a blue ghost.
 * It tries to move in a circle.
 */
public class BlueGhost extends Robot implements Ghost, TickBased {
    /**
     * Creates a new {@link BlueGhost} at the given position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public BlueGhost(int x, int y) {
        super(x, y, Families.GHOST_BLUE);
    }

    /**
     * Turns the robot to the right and then moves in the first direction that is
     * clear.
     */
    @Override
    @StudentImplementationRequired("H2.1")
    public void doMove() {
        org.tudalgo.algoutils.student.Student.crash("H2.1 - Remove if implemented");
    }
}
