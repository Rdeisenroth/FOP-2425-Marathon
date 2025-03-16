package h09;

import h09.abilities.Swims;
import h09.animals.Animal;
import h09.animals.Penguin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static h09.H09_TestUtils.assertDefinedParameters;
import static h09.H09_TestUtils.assertReturnParameter;
import static h09.H09_TestUtils.assertType;
import static h09.H09_TestUtils.getDefinedTypes;
import static h09.H09_TestUtils.getGenericSuperTypes;
import static h09.H09_TestUtils.matchAny;
import static h09.H09_TestUtils.matchNested;
import static h09.H09_TestUtils.matchUpperBounds;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotNull;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class WaterEnclosureTest {

    BasicTypeLink waterEnclosureLink;
    Class<?> waterEnclosureClass;
    Method getStack;
    Method feed;
    Method getMeanElevation;
    Field animals;


    @BeforeEach
    public void setUp() {
        waterEnclosureLink = BasicTypeLink.of(WaterEnclosure.class);
        waterEnclosureClass = waterEnclosureLink.reflection();
        getStack = waterEnclosureLink.getMethod(identical("getStack")).reflection();
        feed = waterEnclosureLink.getMethod(identical("feed")).reflection();
        getMeanElevation = waterEnclosureLink.getMethod(identical("getMeanElevation")).reflection();
        animals = waterEnclosureLink.getField(identical("animals")).reflection();
    }

    @Test
    public void testClassParameter() {
        assertDefinedParameters(WaterEnclosure.class, Set.of(matchUpperBounds("A", Animal.class, Swims.class)));
    }

    @Test
    public void testExtends() {

        List<Type> definedTypes = getDefinedTypes(WaterEnclosure.class, ".*");

        Predicate<Type> genericMatcher = definedTypes.stream()
            .map(type ->
                matchNested(Enclosure.class, matchAny(definedTypes)
                )
            )
            .reduce(Predicate::or)
            .orElse(new H09_TestUtils.GenericPredicate(i -> false, "Expected type is not defined"));

        List<Type> superTypes = getGenericSuperTypes(WaterEnclosure.class);

        assertEquals(2, superTypes.size(), emptyContext(), r -> "WaterEnclosure has an unexpected amount of SuperTypes");

        Context context = contextBuilder()
            .add("actual types", superTypes)
            .add("expected", genericMatcher)
            .build();

        assertTrue(
            superTypes.stream().allMatch((type) -> genericMatcher.test(type) || type == Object.class),
            context,
            r -> "WaterEnclosure has a wrong supertype."
        );
    }

    @Test
    public void testGetStack_field() {
        WaterEnclosure enclosure = new WaterEnclosure();
        enclosure.getStack();

        assertNotNull(
            ReflectionUtils.getFieldValue(enclosure, "animals"),
            emptyContext(),
            r -> "The animals field is not correctly initialized"
        );
    }

    @Test
    public void testGetStack_value() {
        WaterEnclosure enclosure = new WaterEnclosure();

        List<StackOfObjects> stacks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stacks.add(enclosure.getStack());
        }

        for (StackOfObjects stack : stacks) {
            assertEquals(stacks.get(0), stack, emptyContext(), r -> "getStack() does not always return the same object");
        }
    }

    @Test
    public void testGetStack_ReturnType() {
        assertReturnParameter(getStack, matchNested(StackOfObjects.class, matchAny(getDefinedTypes(WaterEnclosure.class, ".*"))));
    }

    @Test
    public void testAnimals_Type() {
        assertType(animals, matchNested(StackOfObjects.class, matchAny(getDefinedTypes(WaterEnclosure.class, ".*"))));
    }

    @Test
    public void testFeed() {

        Enclosure enclosure = new WaterEnclosure();
        List<Penguin> animals = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Penguin mock = mock(Penguin.class, CALLS_REAL_METHODS);
            ReflectionUtils.setFieldValue(mock, "isHungry", false);
            ReflectionUtils.setFieldValue(mock, "elevation", Swims.MAX_ELEVATION);
            if (i % 2 == 0) {
                ReflectionUtils.setFieldValue(mock, "isHungry", true);
            }
            if (i % 3 == 0) {
                ReflectionUtils.setFieldValue(mock, "elevation", Swims.MIN_ELEVATION);
            }

            enclosure.getStack().push(mock);
            animals.add(mock);
        }

        Context context = contextBuilder()
            .add("Animals", animals)
            .build();

        enclosure.feed();

        for (int i = 0; i < 10; i++) {
            if (enclosure.getStack().size() <= 0) {
                fail(context, r -> "WaterEnclosure does not have correct number of Animals after feeding.");
            }
            Penguin mock = (Penguin) enclosure.getStack().pop();

            int id = animals.indexOf(mock);

            if (id % 2 == 0) {
                verify(mock).eat();
                verify(mock).swimDown();
                if (i % 3 == 0) {
                    verify(mock).swimUp();
                } else {
                    verify(mock, never()).swimUp();
                }
            } else {
                verify(mock, never()).eat();
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provide_testGetMeanElevation")
    public void testGetMeanElevation(List<Double> elevations) {

        WaterEnclosure enclosure = new WaterEnclosure<>();

        for (double elevation : elevations) {
            Penguin mock = mock(Penguin.class);
            when(mock.getElevation()).thenReturn((float) elevation);

            enclosure.getStack().push(mock);
        }

        float expected = (float) elevations.stream().mapToDouble(it -> it).average().orElseThrow();

        float actual = enclosure.getMeanElevation();

        Context context = contextBuilder()
            .add("Elevations", elevations)
            .build();

        double error = 0.00001;

        assertTrue(
            Math.abs(Math.abs(expected) - Math.abs(actual)) < error,
            context,
            r -> "Average Elevation is not calculated correctly."
        );
    }

    public static Stream<Arguments> provide_testGetMeanElevation() {
        return Stream.of(
            Arguments.of(List.of(0d, 0d, 0d, 0d, 0d, 0d)),
            Arguments.of(List.of(0d, -1d, -2d, -3d, -4d, -5d)),
            Arguments.of(List.of(-1d, -2d, -3d, -4d, -5d, -6d)),
            Arguments.of(List.of(-6d, -5d, -4d, -3d, -2d, -1d)),
            Arguments.of(List.of(-3d, -5d, 0d, -3d, -10d, -1d)),
            Arguments.of(List.of(-3d, -2d, 0d, -3d, -2d, -3d)),
            Arguments.of(List.of(-2d, -2d, -2d, -2d, -2d, -2d)),
            Arguments.of(List.of(-1d, -2d, -2d))
        );
    }
}
