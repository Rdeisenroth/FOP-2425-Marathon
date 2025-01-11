package h01;

import fopbot.Direction;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import fopbot.Robot;
import h01.template.Families;
import h01.template.Ghost;
import h01.template.TickBased;
import h01.template.Util;

import java.util.ArrayList;

/**
 * The {@link PinkGhost} is a {@link Robot} that looks like a pink ghost.
 * It tries to move in a random direction.
 */
public class PinkGhost extends Robot implements Ghost, TickBased {
    /**
     * Creates a new {@link PinkGhost} at the given position.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public PinkGhost(int x, int y) {
        super(x, y, Families.GHOST_PINK);
    }

    /**
     * Finds in how many directions the ghost can move and then turns a random
     * amount of times to the left.
     * The ghost then moves forward or turns to the left until it can move forward.
     */
    @Override
    @StudentImplementationRequired("H2.2")
    public void doMove() {
        var freeDirections=new ArrayList<Direction>();
        for (int i = 0; i < 4; i++) {
            if(isFrontClear()){
                freeDirections.add(getDirection());
            }
            turnLeft();
        }
        final var toTurn = freeDirections.get(Util.getRandomInteger(1, freeDirections.size())-1);
        while(getDirection() != toTurn){
            turnLeft();
        }
        move();
    }
}
