package h04.chesspieces;

import fopbot.Robot;
import h04.movement.DiagonalMover;
import h04.movement.MoveStrategy;
import h04.template.ChessUtils;

import java.awt.Point;
import java.util.ArrayList;


public class Bishop extends Robot implements DiagonalMover {

    private final Team team;

    public Bishop(final int x, final int y, final Team team){
        super(x, y, team == Team.WHITE ? Families.BISHOP_WHITE : Families.BISHOP_BLACK);
        this.team = team;
    }

    //Wichtig für Implementation
    @Override
    public Team getTeam() { return team;}

    @Override
    public void moveStrategy(int dx, int dy, MoveStrategy strategy) {
        strategy.move(this, dx, dy);
    }

    @Override
    public Point[] getPossibleMoveFields() {
        return getDiagonalMoves();
    }

    //TODO H4.5
}
