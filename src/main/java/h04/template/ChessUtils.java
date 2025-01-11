package h04.template;

import fopbot.World;
import h04.chesspieces.ChessPiece;
import h04.chesspieces.King;
import h04.chesspieces.Team;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.awt.Point;
import java.util.Optional;
import java.util.function.Predicate;

public class ChessUtils {

    public static @Nullable ChessPiece getPieceAt(final int x, final int y) {
        return World.getGlobalWorld().getField(x, y).getEntities()
            .stream()
            .filter(ChessPiece.class::isInstance)
            .map(ChessPiece.class::cast)
            .filter(Predicate.not(ChessPiece::isTurnedOff))
            .findFirst()
            .orElse(null);
    }

    public static @Nullable ChessPiece getPieceAt(final Point p) {
        return getPieceAt(p.x, p.y);
    }

    public static King[] getKings() {
        return World.getGlobalWorld().getAllFieldEntities()
            .stream()
            .filter(King.class::isInstance)
            .map(King.class::cast)
            .toArray(King[]::new);
    }

    /**
     * Returns the team at the given position, or {@code null} if there is no team.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the team at the given position, or {@code null} if there is no team
     */
    public static @Nullable Team getTeamAt(final int x, final int y) {
        return Optional.ofNullable(getPieceAt(x, y))
            .map(ChessPiece::getTeam)
            .orElse(null);
    }

    public static @Nullable Team getTeamAt(final Point p) {
        return getTeamAt(p.x, p.y);
    }

    public static boolean isValidCoordinate(final int x, final int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public static boolean isValidCoordinate(final Point p) {
        return isValidCoordinate(p.x, p.y);
    }

    public static void setFieldColor(final Point field, final @Nullable Color c) {
        World.getGlobalWorld().getField(field.x, field.y).setFieldColor(c);
    }

    public static void colorMoveFields(final Point... fields) {
        for (final Point field : fields) {
            if (field == null)
                continue;
            setFieldColor(field, getPieceAt(field) != null ? Color.RED : Color.GREEN);
        }
    }

    public static void resetFieldColor() {
        for (int y = 0; y < World.getHeight(); y++) {
            for (int x = 0; x < World.getWidth(); x++) {
                setFieldColor(new Point(x, y), null);
            }
        }
    }
}
