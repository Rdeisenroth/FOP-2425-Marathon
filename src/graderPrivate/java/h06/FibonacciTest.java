package h06;

import h06.problems.Fibonacci;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.annotation.SkipAfterFirstFailedTest;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLoop;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static h06.TestUtils.getCtMethod;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertFalse;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.callObject;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotRecursively;

@TestForSubmission
@Timeout(
    value = TestConstants.TEST_TIMEOUT_IN_SECONDS,
    unit = TimeUnit.SECONDS,
    threadMode = Timeout.ThreadMode.SEPARATE_THREAD
)
@SkipAfterFirstFailedTest(TestConstants.SKIP_AFTER_FIRST_FAILED_TEST)
public class FibonacciTest {

    private static Method doTheRecursionMethod;

    @BeforeAll
    public static void setup() throws ReflectiveOperationException {
        doTheRecursionMethod = Fibonacci.class.getDeclaredMethod("doTheRecursion", int.class, int.class, int.class);
        doTheRecursionMethod.setAccessible(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20})
    public void testDoTheRecursion(int n) {
        List<Map<String, Integer>> args = new ArrayList<>();
        Answer<?> answer = invocation -> {
            if (invocation.getMethod().equals(doTheRecursionMethod)) {
                args.add(Map.of(
                    "a", invocation.getArgument(0),
                    "b", invocation.getArgument(1),
                    "n", invocation.getArgument(2)
                ));
            }
            return invocation.callRealMethod();
        };
        Context context = contextBuilder()
            .add("a (first invocation)", 0)
            .add("b (first invocation)", 1)
            .add("n (first invocation)", n)
            .build();

        try (MockedStatic<Fibonacci> ignored = Mockito.mockStatic(Fibonacci.class, answer)) {
            int expected = Fibonacci.fibonacciRecursiveClassic(n);
            int actual = (int) callObject(() -> doTheRecursionMethod.invoke(null, 0, 1, n), context, result ->
                "An exception occurred while invoking method doTheRecursion");
            assertEquals(expected, actual, context, result -> "Method doTheRecursion returned an incorrect value");
        }

        int a = 0;
        int b = 1;
        int intermediateN = n;
        for (int i = 0; i < n; i++) {
            context = contextBuilder()
                .add("a", a)
                .add("b", b)
                .add("n", intermediateN)
                .add("recursion depth", i)  // i.e. how many recursive calls came before this one
                .build();
            if (i + 1 >= args.size()) {
                fail(context, result -> "Expected method doTheRecursion to call itself at least one more time (" + n + " times total)");
            }
            Map<String, Integer> invocation = args.get(i + 1);  // skip first invocation, we only want recursive calls
            assertEquals(b, invocation.get("a"), context, result ->
                "Expected method doTheRecursion to call itself with parameter a = " + result.expected().behavior());
            assertEquals(a + b, invocation.get("b"), context, result ->
                "Expected method doTheRecursion to call itself with parameter b = " + result.expected().behavior());
            assertEquals(intermediateN - 1, invocation.get("n"), context, result ->
                "Expected method doTheRecursion to call itself with parameter n = " + result.expected().behavior());

            // Setup for next iteration
            int tmpA = a;
            int tmpB = b;
            a = b;
            b = tmpA + tmpB;
            intermediateN--;
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20})
    public void testFibonacciRecursiveDifferent(int n) {
        Context context = contextBuilder()
            .add("n", n)
            .build();
        int expected = Fibonacci.fibonacciRecursiveClassic(n);
        int actual = callObject(() -> Fibonacci.fibonacciRecursiveDifferent(n), context, result ->
            "An exception occurred while invoking method fibonacciRecursiveDifferent");
        assertEquals(expected, actual, context, result ->
            "Method fibonacciRecursiveDifferent did not return the same value as fibonacciRecursiveClassic");
    }

    @Test
    public void testFibonacciRecursiveVAnforderung() {
        CtMethod<?> fibonacciRecursiveDifferentCtMethod = getCtMethod(Fibonacci.class, "fibonacciRecursiveDifferent", int.class);
        CtMethod<?> doTheRecursionCtMethod = getCtMethod(Fibonacci.class, "doTheRecursion", int.class, int.class, int.class);

        Iterator<CtElement> fibonacciRecursiveDifferentIterator = fibonacciRecursiveDifferentCtMethod.getBody().descendantIterator();
        boolean callsHelperMethod = false;
        while (!callsHelperMethod && fibonacciRecursiveDifferentIterator.hasNext()) {
            if (fibonacciRecursiveDifferentIterator.next() instanceof CtInvocation<?> ctInvocation) {
                callsHelperMethod = ctInvocation.getExecutable().equals(doTheRecursionCtMethod.getReference());
            }
        }
        assertTrue(callsHelperMethod, emptyContext(), result -> "Method fibonacciRecursiveDifferent does not call doTheRecursion");

        Iterator<CtElement> doTheRecursionIterator = doTheRecursionCtMethod.getBody().descendantIterator();
        boolean usesLoops = false;
        boolean usesIf = false;
        while (!usesLoops && !usesIf && doTheRecursionIterator.hasNext()) {
            CtElement ctElement = doTheRecursionIterator.next();
            if (ctElement instanceof CtLoop) {
                usesLoops = true;
            } else if (ctElement instanceof CtIf) {
                usesIf = true;
            }
        }
        assertFalse(usesLoops, emptyContext(), result -> "Method doTheRecursion uses loops");
        assertFalse(usesIf, emptyContext(), result -> "Method doTheRecursion uses if statements");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20})
    public void testFibonacciIterative(int n) {
        Context context = contextBuilder()
            .add("n", n)
            .build();
        int expected = Fibonacci.fibonacciRecursiveClassic(n);
        int actual = callObject(() -> Fibonacci.fibonacciIterative(n), context, result ->
            "An exception occurred while invoking method fibonacciIterative");
        assertEquals(expected, actual, context, result ->
            "Method fibonacciIterative did not return the same value as fibonacciRecursiveClassic");
    }

    @Test
    public void testFibonacciIterativeVAnforderung() {
        CtMethod<?> fibonacciIterativeCtMethod = getCtMethod(Fibonacci.class, "fibonacciIterative", int.class);
        assertIsNotRecursively(fibonacciIterativeCtMethod, emptyContext(), result ->
            "Method fibonacciIterative uses recursion");
    }
}
