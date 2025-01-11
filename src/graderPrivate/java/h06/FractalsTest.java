package h06;

import h06.problems.Fractals;
import h06.ui.DrawInstruction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.annotation.SkipAfterFirstFailedTest;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static h06.TestUtils.getCtMethod;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertFalse;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotSame;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotIteratively;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotRecursively;

@TestForSubmission
@Timeout(
    value = TestConstants.TEST_TIMEOUT_IN_SECONDS,
    unit = TimeUnit.SECONDS,
    threadMode = Timeout.ThreadMode.SEPARATE_THREAD
)
@SkipAfterFirstFailedTest(TestConstants.SKIP_AFTER_FIRST_FAILED_TEST)
public class FractalsTest {

    private Context concatContext;  // say it three times
    private DrawInstruction[] arr1;
    private DrawInstruction[] arr2;

    private DrawInstruction[] concatenateSetup(JsonParameterSet params) {
        concatContext = params.toContext();
        arr1 = toDrawInstructions(params.get("arr1"));
        arr2 = toDrawInstructions(params.get("arr2"));
        return callObject(() -> Fractals.concatenate(arr1, arr2), concatContext, result ->
            "An exception occurred while invoking method concatenate");
    }

    private Context replaceAtIndexContext;
    private DrawInstruction[] originalArr;
    private DrawInstruction[] arr;
    private int idx;
    private DrawInstruction elem;

    private DrawInstruction[] replaceAtIndexSetup(JsonParameterSet params) {
        replaceAtIndexContext = params.toContext();
        originalArr = toDrawInstructions(params.get("arr"));
        arr = toDrawInstructions(params.get("arr"));
        idx = params.getInt("idx");
        elem = DrawInstruction.valueOf(params.get("elem"));

        return callObject(() -> Fractals.replaceAtIndex(arr, idx, elem), replaceAtIndexContext, result ->
            "An exception occurred while invoking method replaceAtIndex");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void testPowExponentZero(int a) {
        Context context = contextBuilder()
            .add("a", a)
            .add("b", 0)
            .build();
        int expected = 1;
        int actual = callObject(() -> Fractals.pow(a, 0), context, result ->
            "An exception occurred while invoking method pow");
        assertEquals(expected, actual, context, result -> "Method pow returned an incorrect value");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void testPowExponentPositive(int a) {
        Context.Builder<?> contextBuilder = contextBuilder().add("a", a);
        for (int b = 1; b <= 5; b++) {
            Context context = contextBuilder
                .add("b", b)
                .build();
            final int finalB = b;
            int expected = (int) Math.pow(a, b);
            int actual = callObject(() -> Fractals.pow(a, finalB), context, result ->
                "An exception occurred while invoking method pow");
            assertEquals(expected, actual, context, result -> "Method pow returned an incorrect value");
        }
    }

    @Test
    public void testPowVAnforderung() {
        assertIsNotIteratively(getCtMethod(Fractals.class, "pow", int.class, int.class),
            emptyContext(),
            result -> "Method pow is not recursive");
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetBoth.generated.json")
    public void testConcatenateLength(JsonParameterSet params) {
        DrawInstruction[] arrResult = concatenateSetup(params);
        assertEquals(arr1.length + arr2.length, arrResult.length, concatContext, result ->
            "The length of the array returned by method concatenate is incorrect");
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetFirst.generated.json")
    public void testConcatenateFirstOnly(JsonParameterSet params) {
        DrawInstruction[] arrResult = concatenateSetup(params);

        assertEquals(arr1.length, arrResult.length, concatContext, result ->
            "The length of the array returned by method concatenate is incorrect");
        for (int i = 0; i < arr1.length; i++) {
            final int finalI = i;
            assertEquals(arr1[i], arrResult[i], concatContext, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetSecond.generated.json")
    public void testConcatenateSecondOnly(JsonParameterSet params) {
        DrawInstruction[] arrResult = concatenateSetup(params);

        assertEquals(arr2.length, arrResult.length, concatContext, result ->
            "The length of the array returned by method concatenate is incorrect");
        for (int i = 0; i < arr2.length; i++) {
            final int finalI = i;
            assertEquals(arr2[i], arrResult[i], concatContext, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetBoth.generated.json")
    public void testConcatenateBoth(JsonParameterSet params) {
        DrawInstruction[] arrResult = concatenateSetup(params);

        assertEquals(arr1.length + arr2.length, arrResult.length, concatContext, result ->
            "The length of the array returned by method concatenate is incorrect");
        for (int i = 0; i < arr1.length; i++) {
            final int finalI = i;
            assertEquals(arr1[i], arrResult[i], concatContext, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
        for (int i = 0; i < arr2.length; i++) {
            final int finalI = i;
            assertEquals(arr2[i], arrResult[arr1.length + i], concatContext, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    @Test
    public void testConcatenateVAnforderung() {
        CtMethod<?> concatenateCtMethod = getCtMethod(Fractals.class, "concatenate", DrawInstruction[].class, DrawInstruction[].class);
        assertIsNotRecursively(concatenateCtMethod, emptyContext(), result -> "Method concatenate is not iterative");

        Iterator<CtElement> concatenateIterator = concatenateCtMethod.getBody().descendantIterator();
        boolean calledArraycopy = false;
        while (!calledArraycopy && concatenateIterator.hasNext()) {
            if (concatenateIterator.next() instanceof CtInvocation<?> ctInvocation) {
                calledArraycopy = ctInvocation.toStringDebug().startsWith("java.lang.System.arraycopy(");
            }
        }
        assertFalse(calledArraycopy, emptyContext(), result -> "Method concatenate calls System.arraycopy");
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsReplaceAtIndexDataSet.generated.json")
    public void testReplaceAtIndexLength(JsonParameterSet params) {
        DrawInstruction[] arrResult = replaceAtIndexSetup(params);
        assertEquals(arr.length, arrResult.length, replaceAtIndexContext, result ->
            "The length of the array returned by replaceAtIndex does not equal the length of parameter arr");
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsReplaceAtIndexDataSet.generated.json")
    public void testReplaceAtIndexSameElements(JsonParameterSet params) {
        DrawInstruction[] arrResult = replaceAtIndexSetup(params);
        for (int i = 0; i < originalArr.length; i++) {
            if (i == idx) {
                continue;
            }

            final int finalI = i;
            assertEquals(originalArr[i], arrResult[i], replaceAtIndexContext, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsReplaceAtIndexDataSet.generated.json")
    public void testReplaceAtIndexReplacedElement(JsonParameterSet params) {
        DrawInstruction[] arrResult = replaceAtIndexSetup(params);
        if (originalArr.length == 0) {
            return;
        }
        assertEquals(elem, arrResult[idx], replaceAtIndexContext, result ->
            "Value at index %d of the returned array differs from expected value".formatted(idx));
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsReplaceAtIndexDataSet.generated.json")
    public void testReplaceAtIndexNewArray(JsonParameterSet params) {
        DrawInstruction[] arrResult = replaceAtIndexSetup(params);
        assertNotSame(arr, arrResult, replaceAtIndexContext, result ->
            "Method replaceAtIndex did not return a new array");
        for (int i = 0; i < originalArr.length; i++) {
            final int finalI = i;
            assertEquals(originalArr[i], arr[i], replaceAtIndexContext, result ->
                "Input array arr was modified at index " + finalI);
        }
    }

    @Test
    public void testReplaceAtIndexVAnforderung() {
        CtMethod<?> ctMethod = getCtMethod(Fractals.class, "replaceAtIndex", DrawInstruction[].class, int.class, DrawInstruction.class);
        assertIsNotRecursively(ctMethod, emptyContext(), result -> "Method replaceAtIndex is not iterative");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5, -10})
    public void testDragonCurveNonPositive(int n) {
        testDragonCurve(n, new DrawInstruction[] {DrawInstruction.DRAW_LINE});
    }

    @Test
    public void testDragonCurveOne() {
        testDragonCurve(1, new DrawInstruction[] {DrawInstruction.DRAW_LINE, DrawInstruction.TURN_RIGHT, DrawInstruction.DRAW_LINE});
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsDragonCurveDataSet.generated.json")
    public void testDragonCurveN(JsonParameterSet params) {
        int n = params.getInt("n");
        DrawInstruction[] expected = toDrawInstructions(params.get("expected"));
        testDragonCurve(n, expected);
    }

    @Test
    public void testDragonCurveVAnforderung() {
        CtMethod<?> ctMethod = getCtMethod(Fractals.class, "dragonCurve", int.class);
        assertIsNotIteratively(ctMethod, emptyContext(), result -> "Method dragonCurve is not recursive");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5, -10})
    public void testKochSnowflakeNonPositive(int n) {
        testKochSnowflake(n, new DrawInstruction[] {
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.DRAW_LINE,
        });
    }

    @Test
    public void testKochSnowflakeOne() {
        testKochSnowflake(1, new DrawInstruction[] {
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_LEFT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_LEFT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_LEFT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_LEFT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_LEFT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.TURN_RIGHT,
            DrawInstruction.DRAW_LINE,
            DrawInstruction.TURN_LEFT,
            DrawInstruction.DRAW_LINE
        });
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsKochSnowflakeDataSet.generated.json")
    public void testKochSnowflakeN(JsonParameterSet params) {
        int n = params.getInt("n");
        DrawInstruction[] expected = toDrawInstructions(params.get("expected"));
        testKochSnowflake(n, expected);
    }

    private void testDragonCurve(int n, DrawInstruction[] expected) {
        Context context = contextBuilder()
            .add("n", n)
            .build();
        DrawInstruction[] actual = callObject(() -> Fractals.dragonCurve(n), context, result ->
            "An exception occurred while invoking method dragonCurve");

        assertEquals(expected.length, actual.length, context, result ->
            "The length of the returned array differs from expected value");
        for (int i = 0; i < expected.length; i++) {
            final int finalI = i;
            assertEquals(expected[i], actual[i], context, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    private void testKochSnowflake(int n, DrawInstruction[] expected) {
        Context context = contextBuilder()
            .add("n", n)
            .build();
        DrawInstruction[] actual = callObject(() -> Fractals.kochSnowflake(n), context, result ->
            "An exception occurred while invoking method kochSnowflake");

        assertEquals(expected.length, actual.length, context, result ->
            "The length of the returned array differs from expected value");
        for (int i = 0; i < expected.length; i++) {
            final int finalI = i;
            assertEquals(expected[i], actual[i], context, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    private static DrawInstruction[] toDrawInstructions(List<String> drawInstructions) {
        DrawInstruction[] instructions = new DrawInstruction[drawInstructions.size()];
        for (int i = 0; i < instructions.length; i++) {
            instructions[i] = DrawInstruction.valueOf(drawInstructions.get(i));
        }
        return instructions;
    }
}
