package h04.movement;

import fopbot.Robot;
import fopbot.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.objectweb.asm.Type;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.transform.SubmissionExecutionHandler;
import org.tudalgo.algoutils.transform.util.headers.ClassHeader;
import org.tudalgo.algoutils.transform.util.headers.MethodHeader;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.util.Arrays;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.call;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class TeleportingMoveStrategyTest {

    private final MethodHeader teleportingMoveStrategy_move = MethodHeader.of(TeleportingMoveStrategy.class,
        "move", Robot.class, int.class, int.class);

    @AfterEach
    public void tearDown() {
        SubmissionExecutionHandler.resetAll();
    }

    @Test
    public void testClassHeader() {
        ClassHeader originalClassHeader = SubmissionExecutionHandler.getOriginalClassHeader(TeleportingMoveStrategy.class);
        assertTrue(Arrays.stream(originalClassHeader.interfaces()).anyMatch(s -> s.equals(Type.getInternalName(MoveStrategy.class))),
            emptyContext(),
            result -> "Class h04.movement.TeleportingMoveStrategy does not implement interface h04.movement.MoveStrategy");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 4, 5, 6, 8, 9, 10})
    public void testMove(int n) {  // encoding: 4 bits, lower 2: x-coordinate, upper 2: y-coordinate
        SubmissionExecutionHandler.Delegation.disable(teleportingMoveStrategy_move);
        Robot robot = setupEnvironment();
        int expectedX = n & 0b11;
        int expectedY = n >> 2;
        int dx = expectedX - 1;
        int dy = expectedY - 1;

        Context context = contextBuilder()
            .add("robot", robot)
            .add("dx", dx)
            .add("dy", dy)
            .build();
        TeleportingMoveStrategy teleportingMoveStrategyInstance = new TeleportingMoveStrategy();
        call(() -> teleportingMoveStrategyInstance.move(robot, dx, dy), context, result ->
            "An exception occurred while invoking method move");

        assertEquals(expectedX, robot.getX(), context, result -> "Robot was not teleported to correct x-coordinate");
        assertEquals(expectedY, robot.getY(), context, result -> "Robot was not teleported to correct y-coordinate");
    }

    private Robot setupEnvironment() {
        World.setSize(3, 3);
        World.setDelay(0);
        return new Robot(1, 1) {
            @Override
            public void move() {
                // do nothing
            }
        };
    }
}
