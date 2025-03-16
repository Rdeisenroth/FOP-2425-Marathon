package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static h09.H09_TestUtils.assertDefinedParameters;
import static h09.H09_TestUtils.assertParameters;
import static h09.H09_TestUtils.assertReturnParameter;
import static h09.H09_TestUtils.assertType;
import static h09.H09_TestUtils.getDefinedTypes;
import static h09.H09_TestUtils.match;
import static h09.H09_TestUtils.matchNested;
import static h09.H09_TestUtils.matchUpperBounds;
import static h09.H09_TestUtils.matchWildcard;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotNull;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class EnclosureTestPublic {

    BasicTypeLink enclosureLink;
    Method getStack;
    Method forEach;
    Method filterObj;
    Method filterFunc;
    Method eatAndSink;
    Field swimsAtLowElevation;
    Field feedAndSleep;

    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {
        enclosureLink = BasicTypeLink.of(Enclosure.class);

        getStack = enclosureLink.getMethod(identical("getStack")).reflection();
        forEach = enclosureLink.getMethod(identical("forEach")).reflection();
        filterObj = enclosureLink.getMethod(identical("filterObj")).reflection();
        filterFunc = enclosureLink.getMethod(identical("filterFunc")).reflection();
        eatAndSink = enclosureLink.getMethod(identical("EAT_AND_SINK")).reflection();

        swimsAtLowElevation = enclosureLink.getField(identical("SWIMS_AT_LOW_ELEVATION")).reflection();
        feedAndSleep = enclosureLink.getField(identical("FEED_AND_SLEEP")).reflection();
    }

    @Test
    public void testClassParameter() {
        assertDefinedParameters(Enclosure.class, Set.of(matchUpperBounds("A", Animal.class)));
    }

    @Test
    public void testGetStack_ReturnType() {
        Predicate<Type> typeMatcher = getDefinedTypes(Enclosure.class, ".*").stream()
            .map(H09_TestUtils::match)
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        assertReturnParameter(getStack, matchNested(StackOfObjects.class, typeMatcher));
    }

    @Test
    public void testForEach_Parameter() {
        Predicate<Type> typeMatcher = getDefinedTypes(Enclosure.class, ".*").stream()
            .map(type -> matchWildcard(false, type))
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        assertParameters(forEach, List.of(matchNested(Consumer.class, typeMatcher)));
    }

    @Test
    public void testSWIMS_AT_LOW_ELEVATION_Type() {
        assertType(swimsAtLowElevation, matchNested(Predicate.class, match(Swims.class)));
    }

    @ParameterizedTest
    @CsvSource({"5,false", "-3,false", "-5,true", "-5,true"})
    public void testSWIMS_AT_LOW_ELEVATION_Implementation(float elevation, boolean predicateResult) {
        assertNotNull(Enclosure.SWIMS_AT_LOW_ELEVATION, emptyContext(), r -> "SWIMS_AT_LOW_ELEVATION is not implemented");

        try {
            Swims mock = mock(Swims.class);
            when(mock.getElevation()).thenReturn(elevation);

            Context context = contextBuilder().add("elevation", elevation).build();

            assertEquals(
                predicateResult,
                Enclosure.SWIMS_AT_LOW_ELEVATION.test(mock),
                context,
                r -> "SWIMS_AT_LOW_ELEVATION is not implemented correctly"
            );

        } catch (ClassCastException exception) {
            fail(
                emptyContext(),
                r -> "SWIMS_AT_LOW_ELEVATION does not accept correct type of objects. Message of thrown Exception: %s".formatted(
                    exception.getMessage())
            );
        }
    }

    @Test
    public void testFEED_AND_SLEEP_Type() {
        assertType(feedAndSleep, matchNested(Consumer.class, match(Animal.class)));
    }

    @Test
    public void testFEED_AND_SLEEP_Implementation() {
        assertNotNull(Enclosure.FEED_AND_SLEEP, emptyContext(), r -> "FEED_AND_SLEEP is not implemented");

        try {
            Animal mock = mock(Animal.class);

            Enclosure.FEED_AND_SLEEP.accept(mock);

            verify(mock, atLeastOnce()).eat();
            verify(mock, atLeastOnce()).sleep();

        } catch (ClassCastException exception) {
            fail(
                emptyContext(),
                r -> "FEED_AND_SLEEP does not accept correct type of objects. Message of thrown Exception: %s".formatted(exception.getMessage())
            );
        }
    }
}
