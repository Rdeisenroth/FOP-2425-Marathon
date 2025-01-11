package h04.chesspieces;

import fopbot.Robot;
import fopbot.World;
import h04.movement.MoveStrategy;
import h04.movement.OrthogonalMover;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Type;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.transform.SubmissionExecutionHandler;
import org.tudalgo.algoutils.transform.util.headers.ClassHeader;
import org.tudalgo.algoutils.transform.util.headers.MethodHeader;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.awt.Point;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertSame;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.call;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class RookTest {

    private final MethodHeader rook_moveStrategy = MethodHeader.of(Rook.class,
        "moveStrategy", int.class, int.class, MoveStrategy.class);
    private final MethodHeader rook_getPossibleMoveFields = MethodHeader.of(Rook.class,
        "getPossibleMoveFields");

    @AfterEach
    public void tearDown() {
        SubmissionExecutionHandler.resetAll();
    }

    @Test
    public void testClassHeader() {
        ClassHeader originalClassHeader = SubmissionExecutionHandler.getOriginalClassHeader(Rook.class);
        assertTrue(Arrays.stream(originalClassHeader.interfaces()).anyMatch(s -> s.equals(Type.getInternalName(OrthogonalMover.class))),
            emptyContext(),
            result -> "Class h04.chesspieces.Rook does not implement interface h04.movement.OrthogonalMover");
    }

    @Test
    public void testMoveStrategy() {
        SubmissionExecutionHandler.Delegation.disable(rook_moveStrategy);

        World.setSize(3, 3);
        World.setDelay(0);
        AtomicReference<Robot> robotRef = new AtomicReference<>();
        AtomicInteger dxInt = new AtomicInteger(-1);
        AtomicInteger dyInt = new AtomicInteger(-1);
        MoveStrategy moveStrategy = (r, dx, dy) -> {
            robotRef.set(r);
            dxInt.set(dx);
            dyInt.set(dy);
        };
        int dx = 1;
        int dy = 1;
        Context context = contextBuilder()
            .add("dx", dx)
            .add("dy", dy)
            .add("strategy", moveStrategy)
            .build();

        Rook rookInstance = new Rook(0, 0, Team.WHITE);
        call(() -> rookInstance.moveStrategy(dx, dy, moveStrategy), context, result ->
            "An exception occurred while invoking moveStrategy(int, int, MoveStrategy)");
        assertSame(rookInstance, robotRef.get(), context, result ->
            "Method move of MoveStrategy was called with incorrect first parameter");
        assertEquals(dx, dxInt.get(), context, result ->
            "Method move of MoveStrategy was called with incorrect second parameter");
        assertEquals(dy, dyInt.get(), context, result ->
            "Method move of MoveStrategy was called with incorrect third parameter");
    }

    @Test
    public void testGetPossibleMoveFields() {
        SubmissionExecutionHandler.Delegation.disable(rook_getPossibleMoveFields);
        Point[] points = new Point[0];
        SubmissionExecutionHandler.Substitution.enable(MethodHeader.of(OrthogonalMover.class, "getOrthogonalMoves"),
            invocation -> points);

        int worldSize = 8;
        World.setSize(worldSize, worldSize);
        World.setDelay(0);
        Context context = contextBuilder()
            .add("world size", worldSize)
            .add("x", 0)
            .add("y", 0)
            .build();

        Point[] returnValue = callObject(() -> new Rook(0, 0, Team.WHITE).getPossibleMoveFields(), context, result ->
            "An exception occurred while invoking getPossibleMoveFields()");
        assertSame(points, returnValue, context, result -> "Method did not return the result of getOrthogonalMoves()");
    }
}
