package h01;

import fopbot.Direction;
import fopbot.Robot;

public class Util {
    public static void turnTo(Robot r, Direction d) {
        while (r.getDirection() != d){
            r.turnLeft();
        }
    }
}
