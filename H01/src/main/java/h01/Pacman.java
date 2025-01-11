package h01;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import fopbot.Direction;
import fopbot.Robot;
import h01.template.Controllable;
import h01.template.Families;
import h01.template.TickBased;

/**
 * {@link Pacman} is a {@link Robot} that can be controlled by the user and
 * looks like Pacman.
 * It can move in four directions and pick up coins.
 */
public class Pacman extends Robot implements Controllable, TickBased {
    /**
     * Creates a new {@link Pacman} at the given position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Pacman(int x, int y) {
        super(x, y, Families.PACMAN);
    }

    /**
     * Handles the key input of the user.
     * The keys 0, 1, 2, 3 represent the arrow keys up, right, down, left.
     * If the key is not in this range, the method does nothing.
     * If the key is in the range, the robot turns in the corresponding direction,
     * moves one field and collects a coin if there is one.
     *
     * @param k the int value of the pressed key
     */
    @Override
    @StudentImplementationRequired("H1.1")
    public void handleKeyInput(int k) {
        org.tudalgo.algoutils.student.Student.crash("H1.1 - Remove if implemented");
    }
}
