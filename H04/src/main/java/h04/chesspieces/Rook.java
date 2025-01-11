package h04.chesspieces;

import fopbot.Robot;
import h04.movement.MoveStrategy;
import h04.movement.OrthogonalMover;
import h04.template.ChessUtils;

import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Robot implements OrthogonalMover {
    private final Team team;

    public Rook(final int x, final int y, final Team team) {
        super(x, y, team == Team.WHITE ? Families.ROOK_WHITE : Families.ROOK_BLACK);
        this.team = team;
    }

    //Wichtig für Implementation
    @Override
    public Team getTeam() {
        return team;
    }

    //TODO H4.5
    @Override
    public void moveStrategy(int dx, int dy, MoveStrategy strategy) {
        strategy.move(this, dx, dy);
    }

    @Override
    public Point[] getPossibleMoveFields() {
        return getOrthogonalMoves();
    }

}
