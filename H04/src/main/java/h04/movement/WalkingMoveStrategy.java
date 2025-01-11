package h04.movement;

import fopbot.Direction;
import fopbot.Robot;

public class WalkingMoveStrategy implements MoveStrategy {
    @Override
    public void move(final Robot robot, final int dx, final int dy) {
        int x = robot.getX() + dx;
        int y = robot.getY() + dy;
        while (robot.getX() != x) {
            turnTo(robot, robot.getX() > x ? Direction.LEFT : Direction.RIGHT);
            robot.move();
        }
        while (robot.getY() != y) {
            turnTo(robot, robot.getY() > y ? Direction.DOWN : Direction.UP);
            robot.move();
        }
        turnTo(robot,Direction.UP);
    }

    private static void turnTo(Robot r, Direction d) {
        while (r.getDirection() != d) {
            r.turnLeft();
        }
    }
}
