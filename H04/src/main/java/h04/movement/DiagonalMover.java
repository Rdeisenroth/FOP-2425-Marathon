package h04.movement;

import h04.chesspieces.ChessPiece;
import h04.template.ChessUtils;

import java.awt.Point;
import java.util.ArrayList;

public interface DiagonalMover extends ChessPiece {
    default Point[] getDiagonalMoves() {
        var res = new ArrayList<Point>();
        Point step = new Point(1, 1);
        for (int i = 0; i < 4; i++) {
            //noinspection SuspiciousNameCombination
            step = new Point(step.y, -step.x);
            Point pos = new Point(getX(), getY());
            pos = new Point(pos.x + step.x, pos.y + step.y);
            while (ChessUtils.isValidCoordinate(pos) && ChessUtils.getTeamAt(pos) != getTeam()) {
                res.add(pos);
                pos = new Point(pos.x + step.x, pos.y + step.y);
            }
        }
        return res.toArray(Point[]::new);
    }
}
