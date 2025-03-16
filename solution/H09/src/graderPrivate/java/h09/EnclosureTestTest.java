package h09;

import h09.abilities.Swims;
import h09.animals.Penguin;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.reflect.declaration.CtMethod;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class EnclosureTestTest {

    @Test
    public void testTestForEach() {
        CtMethod ctMethod =
            ((BasicMethodLink) BasicTypeLink.of(EnclosureTest.class).getMethod(identical("testForEach"))).getCtElement();

        String body = ctMethod.getBody().toStringDebug();

        long lions = body.lines()
            .filter(line -> line.contains("new h09.animals.Lion"))
            .count();

        long asserts = body.lines()
            .filter(line -> line.contains("org.junit.jupiter.api.Assertions.assertTrue") || line.contains(
                "org.junit.jupiter.api.Assertions.assertFalse"))
            .count();

        boolean hasParameterEnclosure = body.contains("GroundEnclosure<h09.animals.Lion>");

        assertTrue(lions > 2, emptyContext(), r -> "The amount of created Lions does not match expected Amount. Found " + lions);
        assertTrue(asserts > 5, emptyContext(), r -> "The amount of asserts does not match expected Amount. Found " + asserts);
        assertTrue(hasParameterEnclosure, emptyContext(), r -> "Could not find correctly typed Enclosure in test.");
    }

    @Test
    public void testTestFilter_behaviour() {
        try (
            MockedConstruction<WaterEnclosure> mockedConstruction = Mockito.mockConstruction(
                WaterEnclosure.class, withSettings().defaultAnswer(CALLS_REAL_METHODS), (mock, context) -> {
                    StackOfObjects newStack = new StackOfObjects();
                    when(mock.getStack()).thenReturn(newStack);
                }
            )
        ) {
            try (
                MockedConstruction<Penguin> mockedConstructionPenguin = Mockito.mockConstruction(
                    Penguin.class, withSettings().defaultAnswer(CALLS_REAL_METHODS), (mock, context) -> {
                        ReflectionUtils.setFieldValue(mock, "name", context.arguments().get(0));
                        ReflectionUtils.setFieldValue(mock, "age", context.arguments().get(1));
                        ReflectionUtils.setFieldValue(mock, "isHungry", true);

                        if (context.arguments().size() == 3) {
                            ReflectionUtils.setFieldValue(mock, "elevation", context.arguments().get(2));
                            if (mock.getElevation() > Swims.MAX_ELEVATION) {
                                ReflectionUtils.setFieldValue(mock, "elevation", Swims.MAX_ELEVATION);
                            }
                            if (mock.getElevation() < Swims.MIN_ELEVATION) {
                                ReflectionUtils.setFieldValue(mock, "elevation", Swims.MIN_ELEVATION);
                            }
                        }
                    }
                )
            ) {
                new EnclosureTest().testFilter();

                List<WaterEnclosure> enclosures = mockedConstruction.constructed();

                assertTrue(
                    enclosures.stream().anyMatch(mock ->
                        mockingDetails(mock).getInvocations().stream().filter(invocation ->
                            invocation.getMethod().getName().equals("filterFunc")
                        ).count() >= 2
                    ),
                    emptyContext(),
                    r -> "filterFunc was not invoked at least two times on any enclosure"
                );

                assertTrue(
                    enclosures.stream().filter(mock ->
                        mockingDetails(mock).getInvocations().stream().anyMatch(invocation ->
                            invocation.getMethod().getName().equals("filterFunc"))
                    ).count() > 1,
                    emptyContext(),
                    r -> "filterFunc was not invoked on at least two different enclosures"
                );

                for (Penguin penguin : mockedConstructionPenguin.constructed()) {
                    verify(penguin, atLeast(3)).getElevation();
                    verify(penguin, atLeast(1)).isHungry();
                    verify(penguin, atLeast(1)).eat();
                }
            }

        }
    }

    @Test
    public void testTestFilter_test() {
        CtMethod ctMethod =
            ((BasicMethodLink) BasicTypeLink.of(EnclosureTest.class).getMethod(identical("testFilter"))).getCtElement();

        String body = ctMethod.getBody().toStringDebug();

        long penguins = body.lines()
            .filter(line -> line.contains("new h09.animals.Penguin"))
            .count();

        long asserts = body.lines()
            .filter(line -> line.contains("org.junit.jupiter.api.Assertions.assertTrue") || line.contains(
                "org.junit.jupiter.api.Assertions.assertFalse"))
            .count();

        boolean hasParameterEnclosure = body.contains("WaterEnclosure<h09.animals.Penguin>");

        assertTrue(
            penguins >= 2,
            emptyContext(),
            r -> "The amount of created Penguins does not match expected Amount. Found " + penguins
        );
        assertTrue(asserts >= 10, emptyContext(), r -> "The amount of asserts does not match expected Amount. Found " + asserts);
        assertTrue(hasParameterEnclosure, emptyContext(), r -> "Could not find correctly typed Enclosure in test.");


        try (
            MockedConstruction<WaterEnclosure> mockedConstruction = Mockito.mockConstruction(
                WaterEnclosure.class, withSettings().defaultAnswer(CALLS_REAL_METHODS), (mock, context) -> {
                    StackOfObjects newStack = new StackOfObjects();
                    when(mock.getStack()).thenReturn(newStack);

                    doReturn(mock).when(mock).filterFunc(any(), any());

                }
            )
        ) {
            try {
                new EnclosureTest().testFilter();
            } catch (AssertionFailedError e) {
                //correct case
                return;
            }
            fail(emptyContext(), r -> "testFilter did not throw any error when filterFunc always returns all elements");
        }
    }
}
