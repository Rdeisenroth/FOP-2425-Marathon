package h04.chesspieces;

import fopbot.Robot;
//Wichtig für Implementation
//import h04.movement.MoveStrategy;
import h04.template.ChessUtils;

import java.awt.Point;

public class Rook extends Robot {
    private final Team team;

    public Rook(final int x, final int y, final Team team){
        super(x, y, team == Team.WHITE ? Families.ROOK_WHITE : Families.ROOK_BLACK);
        this.team = team;
    }

    //Wichtig für Implementation
    //@Override
    public Team getTeam() {
        return team;
    }

    //TODO H4.5
}
