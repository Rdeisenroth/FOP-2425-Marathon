package h03;

import h03.robots.DoublePowerRobot;
import h03.robots.HackingRobot;
import h03.robots.MovementType;

public class RobotsChallenge {
    private final int winThreshold = 2;
    private int goal;
    private DoublePowerRobot[] robots;
    private int begin;

    public RobotsChallenge(int begin, int goal, DoublePowerRobot[] robots) {
        this.goal = goal;
        this.robots = robots;
        this.begin = begin / 2;
    }

    public int calculateStepsDiagonal() {
        return Math.abs(begin - goal);
    }

    public int calculateStepsOverstep() {
        return calculateStepsDiagonal() % 2 == 0 ? calculateStepsDiagonal() : calculateStepsDiagonal() + 1;
    }

    public int calculateStepsTeleport() {
        return calculateStepsDiagonal() % 2 == 0 ? calculateStepsDiagonal() / 2 : (calculateStepsDiagonal() / 2) + 2;
    }

    public int calculateSteps(MovementType type) {
        return type == MovementType.DIAGONAL ? calculateStepsDiagonal() : type == MovementType.TELEPORT ? calculateStepsTeleport() : calculateStepsOverstep();
    }

    public DoublePowerRobot[] findWinners() {
        var winners = new DoublePowerRobot[robots.length];
        var idx = 0;
        for (DoublePowerRobot robot : robots) {
            int steps = Math.min(calculateSteps(robot.doublePowerTypes[0]), calculateSteps(robot.doublePowerTypes[1]));
            if(steps <=winThreshold){
                winners[idx] = robot;
            }
            idx++;
        }
        return winners;
    }
}
