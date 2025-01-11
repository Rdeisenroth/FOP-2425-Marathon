package h06;

import h06.problems.LinearSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.annotation.SkipAfterFirstFailedTest;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static h06.TestUtils.getCtMethod;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertSame;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.call;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
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
public class LinearSearchTest {

    private int[] arr;
    private int target;
    private int expectedIndex;
    private Context baseContext;

    public void setup(JsonParameterSet params) {
        List<Integer> arrParam = params.get("arr");
        arr = new int[arrParam.size()];
        for (int i = 0; i < arrParam.size(); i++) {
            arr[i] = arrParam.get(i);
        }
        target = params.get("target");
        expectedIndex = params.get("expectedIndex");
        baseContext = params.toContext("expectedIndex");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.generated.json")
    public void testLinearSearchRecursive(JsonParameterSet params) {
        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchRecursive(arr, target), baseContext, result ->
            "An exception occurred while invoking method linearSearchRecursive");
        assertEquals(expectedIndex, actual, baseContext, result -> "Method linearSearchRecursive returned an incorrect value");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.generated.json")
    public void testLinearSearchRecursiveHelperCall(JsonParameterSet params) throws ReflectiveOperationException {
        setup(params);
        Method linearSearchRecursiveHelperMethod = LinearSearch.class.getDeclaredMethod("linearSearchRecursiveHelper", int[].class, int.class, int.class);
        List<Map<String, ?>> args = new ArrayList<>();
        Answer<?> answer = invocation -> {
            if (invocation.getMethod().equals(linearSearchRecursiveHelperMethod)) {
                args.add(Map.of(
                    "arr", invocation.getArgument(0),
                    "target", invocation.getArgument(1),
                    "index", invocation.getArgument(2)
                ));
                return -1;
            } else {
                return invocation.callRealMethod();
            }
        };

        try (MockedStatic<LinearSearch> ignored = Mockito.mockStatic(LinearSearch.class, answer)) {
            call(() -> LinearSearch.linearSearchRecursive(arr, target), baseContext, result ->
                "An exception occurred while invoking method linearSearchRecursive");
        }

        assertEquals(1, args.size(), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper exactly once");
        Map<String, ?> invocation = args.get(0);
        assertSame(arr, invocation.get("arr"), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper with parameter arr");
        assertEquals(target, invocation.get("target"), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper with parameter target");
        assertEquals(0, invocation.get("index"), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper with index = 0");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.generated.json")
    public void testLinearSearchRecursiveHelper(JsonParameterSet params) {
        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchRecursiveHelper(arr, target, 0), baseContext, result ->
            "An exception occurred while invoking method linearSearchRecursiveHelper");
        assertEquals(expectedIndex, actual, baseContext, result -> "Method linearSearchRecursiveHelper returned an incorrect value");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.generated.json")
    public void testLinearSearchRecursiveHelperTargetNotInArray(JsonParameterSet params) {
        if (params.getInt("expectedIndex") != -1) {
            System.out.println("Test ignored. Value target exists in arr");
            return;
        }

        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchRecursiveHelper(arr, target, 0), baseContext, result ->
            "An exception occurred while invoking method linearSearchRecursiveHelper");
        assertEquals(-1, actual, baseContext, result -> "Method linearSearchRecursiveHelper returned an incorrect value");
    }

    @Test
    public void testLinearSearchRecursiveHelperVAnforderung() {
        assertIsNotIteratively(getCtMethod(LinearSearch.class, "linearSearchRecursiveHelper", int[].class, int.class, int.class),
            emptyContext(),
            result -> "Method linearSearchRecursiveHelper is not recursive");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.generated.json")
    public void testLinearSearchIterative(JsonParameterSet params) {
        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchIterative(arr, target), baseContext, result ->
            "An exception occurred while invoking method linearSearchIterative");
        assertEquals(expectedIndex, actual, baseContext, result -> "Method linearSearchIterative returned an incorrect value");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.generated.json")
    public void testLinearSearchIterativeTargetNotInArray(JsonParameterSet params) {
        if (params.getInt("expectedIndex") != -1) {
            System.out.println("Test ignored. Value target exists in arr");
            return;
        }

        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchIterative(arr, target), baseContext, result ->
            "An exception occurred while invoking method linearSearchIterative");
        assertEquals(-1, actual, baseContext, result -> "Method linearSearchIterative returned an incorrect value");
    }

    @Test
    public void testLinearSearchIterativeVAnforderung() {
        assertIsNotRecursively(getCtMethod(LinearSearch.class, "linearSearchIterative", int[].class, int.class),
            emptyContext(),
            result -> "Method linearSearchIterative is not recursive");
    }
}
