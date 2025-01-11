package h04.chesspieces;

import fopbot.Robot;
import h04.movement.MoveStrategy;
import h04.template.ChessUtils;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Robot implements ChessPiece {

    private final Team team;

    public King(final int x, final int y, final Team team) {
        super(x, y, team == Team.WHITE ? Families.KING_WHITE : Families.KING_BLACK);
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public void moveStrategy(final int dx, final int dy, final MoveStrategy strategy) {
        strategy.move(this, dx, dy);
    }

    @Override
    public Point[] getPossibleMoveFields() {
        var res = new ArrayList<Point>();
        for (int dy = -1; dy < 2; dy++) {
            for (int dx = -1; dx < 2; dx++) {
                var pos = new Point(getX() + dx, getY() + dy);
                if (dx == 0 && dy == 0 || !ChessUtils.isValidCoordinate(pos) || ChessUtils.getTeamAt(pos) == getTeam()) {
                    continue;
                }
                res.add(pos);
            }
        }
        return res.toArray(Point[]::new);
    }

    //TODO H4.4
}
