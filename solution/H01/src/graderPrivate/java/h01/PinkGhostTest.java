package h01;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.call;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import fopbot.Direction;
import fopbot.World;
import h01.template.Util;

@TestForSubmission
public class PinkGhostTest {

    private final int startX = 2;
    private final int startY = 2;
    private PinkGhost pinkGhost;
    private Context context;

    @BeforeEach
    public void setup() {
        int worldHeight = 5;
        int worldWidth = 5;
        World.setSize(worldWidth, worldHeight);
        World.setDelay(0);
        World.placeHorizontalWall(startX, startY - 1);
        World.placeHorizontalWall(startX, startY);
        pinkGhost = new PinkGhost(startX, startY);
        context = contextBuilder()
                .add("world height", worldHeight)
                .add("world width", worldWidth)
                .add("pink ghost", pinkGhost)
                .build();
    }

    @Test
    public void testPicksRandomFreePath() throws ReflectiveOperationException {
        Method getRandomIntegerMethod = Util.class.getDeclaredMethod("getRandomInteger", int.class, int.class);
        AtomicInteger min = new AtomicInteger(-1);
        AtomicInteger max = new AtomicInteger(-1);
        Answer<?> answer = invocation -> {
            if (invocation.getMethod().equals(getRandomIntegerMethod)) {
                min.set(invocation.getArgument(0));
                max.set(invocation.getArgument(1));
                return 1;
            } else {
                return invocation.callRealMethod();
            }
        };
        try (MockedStatic<Util> unused = Mockito.mockStatic(Util.class, answer)) {
            pinkGhost.doMove();
        } catch (Exception e) {
            System.err.println("An exception was thrown but I will attempt to salvage it. Details: " + e.getMessage());
        }

        assertEquals(1, min.get(), context, result -> "Method doMove did not call getRandomInteger with min = 1");
        assertEquals(2, max.get(), context, result -> "Method doMove did not call getRandomInteger with max = 2");
    }

    @Test
    public void testTurnToFreePath() throws ReflectiveOperationException {
        Method getRandomIntegerMethod = Util.class.getDeclaredMethod("getRandomInteger", int.class, int.class);
        Answer<?> answer = invocation -> {
            if (invocation.getMethod().equals(getRandomIntegerMethod)) {
                return 2;
            } else {
                return invocation.callRealMethod();
            }
        };
        try (MockedStatic<Util> unused = Mockito.mockStatic(Util.class, answer)) {
            call(pinkGhost::doMove, context, result -> "An exception occurred while invoking doMove");
        }

        assertEquals(Direction.RIGHT, pinkGhost.getDirection(), context,
                result -> "Pink ghost does not face the right direction");
    }

    @Test
    public void testMove() throws ReflectiveOperationException {
        Method getRandomIntegerMethod = Util.class.getDeclaredMethod("getRandomInteger", int.class, int.class);
        Answer<?> answer = invocation -> {
            if (invocation.getMethod().equals(getRandomIntegerMethod)) {
                return 1;
            } else {
                return invocation.callRealMethod();
            }
        };
        try (MockedStatic<Util> unused = Mockito.mockStatic(Util.class, answer)) {
            call(pinkGhost::doMove, context, result -> "An exception occurred while invoking doMove");
        }

        assertEquals(startX + Direction.LEFT.getDx(), pinkGhost.getX(), context,
                result -> "The pink ghost's x-coordinate is incorrect");
        assertEquals(startY + Direction.LEFT.getDy(), pinkGhost.getY(), context,
                result -> "The pink ghost's y-coordinate is incorrect");
    }
}
