package h06;

import h06.problems.Fractals;
import h06.ui.DrawInstruction;
import h06.ui.FractalVisualizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.annotation.SkipAfterFirstFailedTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
@Timeout(
    value = TestConstants.TEST_TIMEOUT_IN_SECONDS,
    unit = TimeUnit.SECONDS,
    threadMode = Timeout.ThreadMode.SEPARATE_THREAD
)
@SkipAfterFirstFailedTest(TestConstants.SKIP_AFTER_FIRST_FAILED_TEST)
public class MainTest {

    private List<DrawInstruction[]> dragonCurveReturnValues;
    private List<DrawInstruction[]> kochSnowflakeReturnValues;
    private Answer<?> fractalsAnswer;
    private List<List<?>> constructorArgs;
    private MockedConstruction.MockInitializer<FractalVisualizer> initializer;

    @BeforeEach
    public void setup() throws ReflectiveOperationException {
        dragonCurveReturnValues = new ArrayList<>();
        kochSnowflakeReturnValues = new ArrayList<>();
        Method dragonCurveMethod = Fractals.class.getDeclaredMethod("dragonCurve", int.class);
        Method kochSnowflakeMethod = Fractals.class.getDeclaredMethod("kochSnowflake", int.class);
        fractalsAnswer = invocation -> {
            if (invocation.getMethod().equals(dragonCurveMethod)) {
                DrawInstruction[] drawInstructions = new DrawInstruction[0];
                dragonCurveReturnValues.add(drawInstructions);
                return drawInstructions;
            } else if (invocation.getMethod().equals(kochSnowflakeMethod)) {
                DrawInstruction[] drawInstructions = new DrawInstruction[0];
                kochSnowflakeReturnValues.add(drawInstructions);
                return drawInstructions;
            } else {
                return invocation.callRealMethod();
            }
        };

        constructorArgs = new ArrayList<>();
        initializer = (mock, context) -> {
            constructorArgs.add(context.arguments());
        };
    }

    @Test
    public void testMainDragonCurve() {
        try (MockedStatic<Fractals> fractalsMock = Mockito.mockStatic(Fractals.class, fractalsAnswer);
             MockedConstruction<FractalVisualizer> visualizerMock = Mockito.mockConstruction(FractalVisualizer.class, initializer)) {
            Main.main(new String[0]);
        }

        boolean calledConstructorWithDragonCurveParams = constructorArgs.stream()
            .filter(list -> list.get(1).equals(90))
            .map(list -> list.get(0))
            .anyMatch(dragonCurveReturnValues::contains);
        assertTrue(calledConstructorWithDragonCurveParams, emptyContext(), result ->
            "Constructor of FractalVisualizer was not called with args (<result of Fractals.dragonCurve(n)>, 90)");
    }

    @Test
    public void testMainKochSnowflake() throws ReflectiveOperationException {
        try (MockedStatic<Fractals> fractalsMock = Mockito.mockStatic(Fractals.class, fractalsAnswer);
             MockedConstruction<FractalVisualizer> visualizerMock = Mockito.mockConstruction(FractalVisualizer.class, initializer)) {
            Main.main(new String[0]);
        }

        boolean calledConstructorWithKochSnowflakeParams = constructorArgs.stream()
            .filter(list -> list.get(1).equals(60))
            .map(list -> list.get(0))
            .anyMatch(kochSnowflakeReturnValues::contains);
        assertTrue(calledConstructorWithKochSnowflakeParams, emptyContext(), result ->
            "Constructor of FractalVisualizer was not called with args (<result of Fractals.kochSnowflake(n)>, 60)");
    }
}
