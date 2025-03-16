package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import h09.animals.Penguin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static h09.H09_TestUtils.assertDefinedParameters;
import static h09.H09_TestUtils.assertParameters;
import static h09.H09_TestUtils.assertReturnParameter;
import static h09.H09_TestUtils.assertType;
import static h09.H09_TestUtils.getDefinedTypes;
import static h09.H09_TestUtils.match;
import static h09.H09_TestUtils.matchNested;
import static h09.H09_TestUtils.matchUpperBounds;
import static h09.H09_TestUtils.matchWildcard;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
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
public class EnclosureTestPrivate {

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
    public void testFilterObj_Parameter() {
        Predicate<Type> typeMatcher = getDefinedTypes(Enclosure.class, ".*").stream()
            .map(type -> matchWildcard(false, type))
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        assertParameters(filterObj, List.of(matchNested(Predicate.class, typeMatcher)));
    }

    @Test
    public void testFilterFunc_ParameterDefinition() {
        Predicate<Type> classTypeMatcher = getDefinedTypes(Enclosure.class, ".*").stream()
            .map(H09_TestUtils::match)
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        Predicate<Type> typeMatcher = matchUpperBounds("E", matchNested(Enclosure.class, classTypeMatcher));

        assertDefinedParameters(filterFunc, Set.of(typeMatcher));
    }

    @Test
    public void testFilterFunc_Parameter() {
        Predicate<Type> classTypeMatcher = getDefinedTypes(Enclosure.class, ".*").stream()
            .map(H09_TestUtils::match)
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        Predicate<Type> methodTypeMatcher = getDefinedTypes(filterFunc, ".*").stream()
            .map(H09_TestUtils::match)
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        assertParameters(
            filterFunc, List.of(
                matchNested(Supplier.class, matchWildcard(true, methodTypeMatcher)),
                matchNested(Predicate.class, matchWildcard(false, classTypeMatcher))
            )
        );
    }

    @Test
    public void testFilterFunc_ReturnType() {
        Predicate<Type> methodTypeMatcher = getDefinedTypes(Enclosure.class, ".*").stream()
            .map(H09_TestUtils::match)
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        assertReturnParameter(getStack, matchNested(StackOfObjects.class, methodTypeMatcher));
    }

    @ParameterizedTest
    @MethodSource("provide_testFilterFunc_Implementation")
    public void testFilterFunc_Implementation(int animalCount, List<Boolean> filterResults) {

        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < animalCount; i++) {
            animals.add(mock(Animal.class));
        }

        Enclosure enclosure = mock(Enclosure.class, CALLS_REAL_METHODS);
        StackOfObjects stack = new StackOfObjects();
        when(enclosure.getStack()).thenReturn(stack);

        for (Animal animal : animals) {
            enclosure.getStack().push(animal);
        }

        Supplier supplier = () -> {
            Enclosure mock = mock(Enclosure.class, CALLS_REAL_METHODS);

            StackOfObjects newStack = new StackOfObjects();
            when(mock.getStack()).thenReturn(newStack);

            return mock;
        };

        Predicate filter = (object) -> filterResults.get(animals.indexOf(object));

        Enclosure actual = enclosure.filterFunc(supplier, filter);
        StackOfObjects<Animal> actualStack = actual.getStack();

        Set<Animal> actualAnimals = new HashSet<>();

        while (actualStack.size() > 0) {
            actualAnimals.add(actualStack.pop());
        }

        Set<Animal> expectedAnimals = (Set<Animal>) animals.stream().filter(filter).collect(Collectors.toSet());

        Context context = contextBuilder()
            .add("Animal count", animalCount)
            .add("Filter results", filterResults)
            .build();

        assertEquals(expectedAnimals, actualAnimals, context, r -> "Returned Enclosure does not contain correct animals.");
    }

    public static Stream<Arguments> provide_testFilterFunc_Implementation() {
        return Stream.of(
            Arguments.of(1, List.of(true)),
            Arguments.of(1, List.of(false)),
            Arguments.of(5, List.of(true, true, true, true, true)),
            Arguments.of(5, List.of(false, false, false, false, false)),
            Arguments.of(5, List.of(false, true, false, true, false)),
            Arguments.of(5, List.of(false, true, true, true, false)),
            Arguments.of(5, List.of(true, true, false, true, false))
        );
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

    @Test
    public void testEAT_AND_SINK_DefinedType() {
        assertDefinedParameters(eatAndSink, Set.of(matchUpperBounds("T", Animal.class, Swims.class)));
    }

    @Test
    public void testEAT_AND_SINK_ReturnType() {
        Predicate<Type> methodTypeMatcher = getDefinedTypes(eatAndSink, ".*").stream()
            .map(H09_TestUtils::match)
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        assertReturnParameter(eatAndSink, matchNested(Consumer.class, methodTypeMatcher));
    }

    @Test
    public void testEAT_AND_SINK_Implementation() {
        Consumer consumer = Enclosure.EAT_AND_SINK();
        assertNotNull(consumer, emptyContext(), r -> "EAT_AND_SINK is not implemented correctly");
        try {
            Penguin mock = mock(Penguin.class);

            consumer.accept(mock);

            verify(mock, atLeastOnce()).eat();
            verify(mock, atLeastOnce()).swimDown();

        } catch (ClassCastException exception) {
            fail(
                emptyContext(),
                r -> "EAT_AND_SINK does not accept correct type of objects. Message of thrown Exception: %s".formatted(exception.getMessage())
            );
        }
    }
}
