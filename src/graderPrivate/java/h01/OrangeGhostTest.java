package h01;

import fopbot.Direction;
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
public class OrangeGhostTest {

    private final int startX = 1;
    private final int startY = 1;
    private OrangeGhost orangeGhost;
    private Context context;

    @BeforeEach
    public void setup() {
        int worldHeight = 3;
        int worldWidth = 3;
        World.setSize(worldWidth, worldHeight);
        World.setDelay(0);
        orangeGhost = new OrangeGhost(startX, startY);
        context = contextBuilder()
            .add("world height", worldHeight)
            .add("world width", worldWidth)
            .add("orange ghost", orangeGhost)
            .build();
    }

    @Test
    public void testMoveForward() {
        call(orangeGhost::doMove, context, result -> "An exception occurred while invoking doMove");  // move up

        assertEquals(startX, orangeGhost.getX(), context, result -> "Orange ghost's x-coordinate is incorrect");
        assertEquals(startY + 1, orangeGhost.getY(), context, result -> "Orange ghost's y-coordinate is incorrect");
    }

    @Test
    public void testTurnsRight() {
        call(orangeGhost::doMove, context, result -> "An exception occurred while invoking doMove");  // move up
        call(orangeGhost::doMove, context, result -> "An exception occurred while invoking doMove");  // turn right

        assertEquals(Direction.RIGHT, orangeGhost.getDirection(), context, result -> "Orange ghost is not facing right");
        List<Transition.RobotAction> expected = List.of(
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.NONE
        );
        List<Transition.RobotAction> actual = getRobotActions(orangeGhost, 1, 5);
        assertEquals(expected, actual, context, result -> "Orange ghost did not perform the expected actions (1x right turn)");
    }

    @Test
    public void testSwitchTurning() {
        call(orangeGhost::doMove, context, result -> "An exception occurred while invoking doMove");  // move up
        call(orangeGhost::doMove, context, result -> "An exception occurred while invoking doMove");  // turn right
        call(orangeGhost::doMove, context, result -> "An exception occurred while invoking doMove");  // move right
        call(orangeGhost::doMove, context, result -> "An exception occurred while invoking doMove");  // turn left

        assertEquals(Direction.LEFT, orangeGhost.getDirection(), context, result -> "Orange ghost is not facing left");
        List<Transition.RobotAction> expected = List.of(
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.TURN_LEFT,
            Transition.RobotAction.NONE
        );
        List<Transition.RobotAction> actual = getRobotActions(orangeGhost, 5, 8);
        assertEquals(expected, actual, context, result -> "Orange ghost did not perform the expected actions (2x left turn)");
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
