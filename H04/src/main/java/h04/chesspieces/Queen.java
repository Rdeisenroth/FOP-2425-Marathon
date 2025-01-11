package h04.chesspieces;

import fopbot.Robot;
import h04.movement.DiagonalMover;
import h04.movement.MoveStrategy;
import h04.movement.OrthogonalMover;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Robot implements OrthogonalMover, DiagonalMover {
    private final Team team;

    public Queen(final int x, final int y, final Team team) {
        super(x, y, team == Team.WHITE ? Families.QUEEN_WHITE : Families.QUEEN_BLACK);
        this.team = team;
    }

    //Wichtig für Implementation
    //@Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void moveStrategy(int dx, int dy, MoveStrategy strategy) {
        strategy.move(this, dx, dy);
    }

    @Override
    public Point[] getPossibleMoveFields() {
        var res = new ArrayList<Point>();
        res.addAll(Arrays.asList(getDiagonalMoves()));
        res.addAll(Arrays.asList(getOrthogonalMoves()));
        return res.toArray(Point[]::new);
    }
}
