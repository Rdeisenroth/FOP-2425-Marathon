package h01;

import fopbot.Robot;
import fopbot.Transition;
import fopbot.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.List;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class BlueGhostTest {

    private BlueGhost blueGhost;
    private Context context;

    @BeforeEach
    public void setup() {
        int worldHeight = 5;
        int worldWidth = 5;
        int startX = 2;
        int startY = 2;
        World.setSize(worldWidth, worldHeight);
        World.setDelay(0);
        World.placeVerticalWall(startX, startY);
        World.placeHorizontalWall(startX, startY);
        blueGhost = new BlueGhost(startX, startY);
        context = contextBuilder()
            .add("world width", worldWidth)
            .add("world height", worldHeight)
            .add("blue ghost", blueGhost)
            .build();
    }

    @Test
    public void testRightTurn() {
        call(blueGhost::doMove, context, result -> "An exception was thrown while invoking doMove");

        List<Transition.RobotAction> expected = List.of(
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.TURN_LEFT
        );
        List<Transition.RobotAction> actual = getRobotActions(blueGhost, 0, 3);
        assertEquals(expected, actual, context, result -> "Blue ghost did not turn right");
    }

    @Test
    public void testLeftTurns() {
        call(blueGhost::doMove, context, result -> "An exception was thrown while invoking doMove");

        List<Transition.RobotAction> expected = List.of(
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.TURN_LEFT
        );
        List<Transition.RobotAction> actual = getRobotActions(blueGhost, 3, 5);
        assertEquals(expected, actual, context, result -> "Blue ghost did not turn left until not facing a wall");
    }

    @Test
    public void testMove() {
        call(blueGhost::doMove, context, result -> "An exception was thrown while invoking doMove");

        List<Transition.RobotAction> expected = List.of(
            Transition.RobotAction.MOVE,
            Transition.RobotAction.NONE
        );
        List<Transition.RobotAction> actual = getRobotActions(blueGhost, 5, 7);
        assertEquals(expected, actual, context, result -> "Blue ghost did not move one step");
    }

    private static List<Transition.RobotAction> getRobotActions(Robot robot, int from, int to) {
        return World.getGlobalWorld()
            .getTrace(robot)
            .getTransitions()
            .subList(from, to)
            .stream()
            .map(transition -> transition.action)
            .toList();
    }
}
