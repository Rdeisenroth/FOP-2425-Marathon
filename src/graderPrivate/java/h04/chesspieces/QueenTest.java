package h04.chesspieces;

import fopbot.World;
import h04.movement.DiagonalMover;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotNull;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class QueenTest {

    private final MethodHeader queen_moveStrategy = MethodHeader.of(Queen.class,
        "moveStrategy", int.class, int.class, MoveStrategy.class);
    private final MethodHeader queen_getPossibleMoveFields = MethodHeader.of(Queen.class, "getPossibleMoveFields");

    @AfterEach
    public void tearDown() {
        SubmissionExecutionHandler.resetAll();
    }

    @Test
    public void testClassHeader() {
        ClassHeader originalClassHeader = SubmissionExecutionHandler.getOriginalClassHeader(Queen.class);
        assertTrue(Arrays.stream(originalClassHeader.interfaces()).anyMatch(s -> s.equals(Type.getInternalName(OrthogonalMover.class))),
            emptyContext(),
            result -> "Class h04.chesspieces.Queen does not implement interface h04.movement.OrthogonalMover");
        assertTrue(Arrays.stream(originalClassHeader.interfaces()).anyMatch(s -> s.equals(Type.getInternalName(DiagonalMover.class))),
            emptyContext(),
            result -> "Class h04.chesspieces.Queen does not implement interface h04.movement.DiagonalMover");
    }

    @Test
    public void testGetPossibleMoveFields_Correct() {
        SubmissionExecutionHandler.Delegation.disable(queen_getPossibleMoveFields);
        int worldSize = 8;

        for (int x : new int[] {0, worldSize - 1}) {
            for (int y : new int[] {0, worldSize - 1}) {
                World.setSize(worldSize, worldSize);
                World.setDelay(0);
                Queen queenInstance = new Queen(x, y, Team.WHITE);
                Context context = contextBuilder()
                    .add("world size", worldSize)
                    .add("x-coordinate", x)
                    .add("y-coordinate", y)
                    .build();
                Point[] returnValue = callObject(queenInstance::getPossibleMoveFields, context, result ->
                    "An exception occurred while invoking getPossibleMoveFields()");

                assertNotNull(returnValue, context, result -> "getPossibleMoveFields() returned null");
                List<Point> points = Arrays.stream(returnValue).filter(Objects::nonNull).collect(Collectors.toList());
                Point[] vectors = new Point[] {
                    new Point(0, 1),
                    new Point(1, 1),
                    new Point(1, 0),
                    new Point(1, -1),
                    new Point(0, -1),
                    new Point(-1, -1),
                    new Point(-1, 0),
                    new Point(-1, 1),
                };
                for (Point vector : vectors) {
                    for (int n = 1; n < worldSize; n++) {
                        if (vector.x * n + x < 0 || vector.x * n + x >= worldSize || vector.y * n + y < 0 || vector.y * n + y >= worldSize) {
                            break;
                        }
                        Point expectedPoint = new Point(vector.x * n + x, vector.y * n + y);
                        assertTrue(points.contains(expectedPoint), context, result ->
                            "Point %s was not found in returned array".formatted(expectedPoint));
                        points.remove(expectedPoint);
                    }
                }
                assertEquals(Collections.emptyList(), points, context, result -> "Returned array contained more points than are valid");
            }
        }
    }

    @Test
    public void testGetPossibleMoveFields_Combine() {
        SubmissionExecutionHandler.Delegation.disable(queen_getPossibleMoveFields);
        int worldSize = 8;
        World.setSize(worldSize, worldSize);
        World.setDelay(0);

        Queen queenInstance = new Queen(1, 1, Team.WHITE);
        Context context = contextBuilder()
            .add("world size", worldSize)
            .add("x-coordinate", queenInstance.getX())
            .add("y-coordinate", queenInstance.getY())
            .build();
        Point[] returnValue = callObject(queenInstance::getPossibleMoveFields, context, result ->
            "An exception occurred while invoking getPossibleMoveFields()");

        assertNotNull(returnValue, context, result -> "getPossibleMoveFields() returned null");
        Set<Point> expectedPoints = Stream.of(queenInstance.getDiagonalMoves(), queenInstance.getOrthogonalMoves())
            .flatMap(Stream::of)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
        Set<Point> actualPoints = Arrays.stream(returnValue).filter(Objects::nonNull).collect(Collectors.toSet());
        assertEquals(expectedPoints, actualPoints, context, result ->
            "Method did not merge results of getOrthogonalMoves() and getDiagonalMoves()");
    }
}
