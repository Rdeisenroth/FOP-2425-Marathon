package h04.movement;


import fopbot.Robot;

public interface MoveStrategy {
    public void move(Robot robot, int dx, int dy);
}
