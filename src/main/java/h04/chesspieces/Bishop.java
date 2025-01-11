package h04.chesspieces;

import fopbot.Robot;
//Wichtig für Implementation
//import h04.movement.MoveStrategy;

import java.awt.Point;


public class Bishop extends Robot {

    private final Team team;

    public Bishop(final int x, final int y, final Team team){
        super(x, y, team == Team.WHITE ? Families.BISHOP_WHITE : Families.BISHOP_BLACK);
        this.team = team;
    }

    //Wichtig für Implementation
    //@Override
    public Team getTeam() { return team;}

    //TODO H4.5
}
