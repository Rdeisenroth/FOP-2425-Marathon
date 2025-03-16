package h09;

import h09.abilities.Swims;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.fail;
import static org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers.identical;

@TestForSubmission
public class WaterEnclosureTestPublic {

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
    public void testGetMeanElevation(List<Double> elevations, double expected) {

        WaterEnclosure enclosure = new WaterEnclosure<>();

        for (double elevation : elevations) {
            Penguin mock = mock(Penguin.class);
            when(mock.getElevation()).thenReturn((float) elevation);

            enclosure.getStack().push(mock);
        }

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
            Arguments.of(List.of(0d, 0d, 0d, 0d, 0d, 0d), 0.0),
            Arguments.of(List.of(0d, -1d, -2d, -3d, -4d, -5d), -2.5),
            Arguments.of(List.of(-1d, -2d, -3d, -4d, -5d, -6d), -3.5),
            Arguments.of(List.of(-6d, -5d, -4d, -3d, -2d, -1d), -3.5),
            Arguments.of(List.of(-3d, -5d, 0d, -3d, -10d, -1d), -3.6666667),
            Arguments.of(List.of(-3d, -2d, 0d, -3d, -2d, -3d), -2.1666667),
            Arguments.of(List.of(-2d, -2d, -2d, -2d, -2d, -2d), -2.0),
            Arguments.of(List.of(-1d, -2d, -2d), -1.6666666)
        );
    }
}
