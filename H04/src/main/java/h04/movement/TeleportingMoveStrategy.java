package h04.movement;

import fopbot.Robot;

public class TeleportingMoveStrategy implements MoveStrategy{
    @Override
    public void move(Robot robot, int dx, int dy) {
        robot.setX(robot.getX() + dx);
        robot.setY(robot.getY() + dy);
    }
}
