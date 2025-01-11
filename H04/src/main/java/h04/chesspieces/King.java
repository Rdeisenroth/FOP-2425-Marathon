package h04.chesspieces;

import fopbot.Robot;
//Wichtig für Implementation
//import h04.movement.MoveStrategy;
import h04.template.ChessUtils;

import java.awt.Point;

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

    //TODO H4.4
}
