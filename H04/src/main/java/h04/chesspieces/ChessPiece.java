package h04.chesspieces;
//Wichtig für Implementation
//import h04.movement.MoveStrategy;

import java.awt.Point;

public interface ChessPiece {
    Team getTeam();
    int getX();
    int getY();
    /**
     * Returns {@code true} if this robot is turned off.
     *
     * @return {@code true} if this robot is turned off
     */
    boolean isTurnedOff();
    void turnOff();

    //TODO H4.3
}
