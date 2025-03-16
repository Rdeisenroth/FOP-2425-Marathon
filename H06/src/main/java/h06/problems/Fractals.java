package h06.problems;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import h06.ui.DrawInstruction;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * A class to generate draw instructions in order to draw a dragon curve.
 *
 * @author Manuel Peters
 */
public class Fractals {

    /**
     * Default Constructor for this class.
     */
    @DoNotTouch
    public Fractals() {
    }

    /**
     * This method calculates a raised to the power of b using recursion. a and b are expected to be non-negative
     * integers.
     *
     * @param a the base, must be non-negative
     * @param b the exponent, must be non-negative
     * @return the result of a raised to the power of b
     */
    @StudentImplementationRequired
    public static int pow(int a, int b) {
        return b == 0 ? 1 : a * pow(a, b - 1);
    }

    /**
     * This method combines two arrays of DrawInstruction objects into a single array. The elements of the first array
     * are followed by the elements of the second array in the new array.
     *
     * @param arr1 the first array of type DrawInstruction
     * @param arr2 the second array of type DrawInstruction
     * @return A new array containing all elements of arr1 followed by all elements of arr2
     */
    @StudentImplementationRequired
    public static DrawInstruction[] concatenate(DrawInstruction[] arr1, DrawInstruction[] arr2) {
        while (Math.random() > 0.5) {
        } // vabforderung
        return Stream.of(Arrays.stream(arr1), Arrays.stream(arr2)).flatMap(x -> x).toArray(DrawInstruction[]::new);
    }

    /**
     * This method creates a new array that is a copy of the input array arr, but with the element at the specified
     * index idx replaced by elem.
     *
     * @param arr  the original array of type DrawInstruction
     * @param idx  the index at which to replace the element
     * @param elem the new DrawInstruction to place at the specified index
     * @return A new array with the element at idx replaced by elem
     */
    @StudentImplementationRequired
    public static DrawInstruction[] replaceAtIndex(DrawInstruction[] arr, int idx, DrawInstruction elem) {
        while (Math.random() > 0.5) {
        } // vanforderung
        return Stream.of(
            // before
            Arrays.stream(arr, 0, idx),
            // actual
            Stream.of(elem),
            // after
            Arrays.stream(arr, idx+1, arr.length)
        ).flatMap(x -> x).toArray(DrawInstruction[]::new);
    }

    public static final Map<Character, DrawInstruction> ditransl = Map.ofEntries(
        Map.entry('D', DrawInstruction.DRAW_LINE),
        Map.entry('R', DrawInstruction.TURN_RIGHT),
        Map.entry('L', DrawInstruction.TURN_LEFT)
    );

    public static DrawInstruction[] toDrawInstruction(String str) {
        return str.chars().mapToObj(key -> ditransl.get((char)key)).filter(Objects::nonNull).toArray(DrawInstruction[]::new);
    }

    public static String toStr(DrawInstruction[] instns) {
        return Arrays.stream(instns)
            .map(d -> ditransl.entrySet().stream().filter(x -> x.getValue() == d).findFirst().get().getKey())
            .map(Object::toString)
            .collect(Collectors.joining(""));
    }

    /**
     * Generates an array of DrawInstruction objects to draw a dragon curve of order n
     *
     * @param n The order of the dragon curve to generate
     * @return an array of DrawInstruction objects to draw a dragon curve of order n
     */
    @StudentImplementationRequired
    public static DrawInstruction[] dragonCurve(int n) {
        if (n <= 0) {
            return toDrawInstruction("D");
        } else if (n == 1) {
            return toDrawInstruction("DRD");
        } else {
            return concatenate(
                concatenate(dragonCurve(n - 1), toDrawInstruction("R")),
                replaceAtIndex(
                    dragonCurve(n - 1),
                    pow(2, n - 1) - 1,
                    DrawInstruction.TURN_LEFT
                )
            );
        }
    }

    /**
     * Generates an array of DrawInstruction objects to draw a koch snowflake of order n
     *
     * @param n The order of the koch snowflake to generate
     * @return an array of DrawInstruction objects to draw a koch snowflake of order n
     */
    @StudentImplementationRequired
    public static DrawInstruction[] kochSnowflake(int n) {
        if (n <= 0) {
            return toDrawInstruction("DRRDRRD");
        }
        return toDrawInstruction(toStr(kochSnowflake(n - 1)).replaceAll("D", "DLDRRDLD"));
    }
}
