package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;
import h01.template.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;

@TestForSubmission
public class RedGhostTest {

    private final int startX = 1;
    private final int startY = 1;
    private Pacman chased;
    private RedGhost redGhost;
    private Context context;

    @BeforeEach
    public void setup() {
        int worldHeight = 3;
        int worldWidth = 3;
        World.setSize(worldWidth, worldHeight);
        World.setDelay(0);
        World.placeVerticalWall(startX, startY);
        World.placeHorizontalWall(startX, startY);
        chased = new Pacman(worldWidth - 1, worldHeight - 1);
        redGhost = new RedGhost(startX, startY, chased);
        context = contextBuilder()
            .add("world height", worldHeight)
            .add("world width", worldWidth)
            .add("chased", chased)
            .add("red ghost", redGhost)
            .build();
    }

    @Test
    public void testTurnToPacman() throws ReflectiveOperationException {
        AtomicReference<Robot> chasedRef = new AtomicReference<>();
        AtomicReference<Robot> chaserRef = new AtomicReference<>();
        Method furthestDirectionMethod = Util.class.getDeclaredMethod("furthestDirection", Robot.class, Robot.class);
        Answer<?> answer = invocation -> {
            if (invocation.getMethod().equals(furthestDirectionMethod)) {
                chasedRef.set(invocation.getArgument(0));
                chaserRef.set(invocation.getArgument(1));
            }
            return invocation.callRealMethod();
        };
        try (MockedStatic<Util> ignored = Mockito.mockStatic(Util.class, answer)) {
            call(redGhost::doMove, context, result -> "An exception occurred while invoking doMove");
        }

        assertSame(chased, chasedRef.get(), context, result -> "doMove did not call furthestDirection with chased = " + chased);
        assertSame(redGhost, chaserRef.get(), context, result -> "doMove did not call furthestDirection with chaser = " + redGhost);
    }

    @Test
    public void testTurnLeft() {
        call(redGhost::doMove, context, result -> "An exception occurred while invoking doMove");

        assertEquals(Direction.LEFT, redGhost.getDirection(), context, result -> "Red ghost does not face the correct direction");
    }

    @Test
    public void testMove() {
        call(redGhost::doMove, context, result -> "An exception occurred while invoking doMove");

        assertEquals(startX - 1, redGhost.getX(), context, result -> "Red ghost's x-coordinate is incorrect");
        assertEquals(startY, redGhost.getY(), context, result -> "Red ghost's y-coordinate is incorrect");
    }
}
