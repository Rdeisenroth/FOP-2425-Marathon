package h06.problems;

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
    public Fractals() {}

    /**
     * This method calculates a raised to the power of b using recursion.
     * a and b are expected to be non-negative integers.
     *
     * @param a the base, must be non-negative
     * @param b the exponent, must be non-negative
     * @return the result of a raised to the power of b
     */
    @StudentImplementationRequired
    public static int pow(int a, int b) {
        if(b == 0) {
            return 1;
        } else {
            return a * pow(a, b-1);
        }
    }

    /**
     * This method combines two arrays of DrawInstruction objects into a single array.
     * The elements of the first array are followed by the elements of the second array in the new array.
     *
     * @param arr1 the first array of type DrawInstruction
     * @param arr2 the second array of type DrawInstruction
     * @return A new array containing all elements of arr1 followed by all elements of arr2
     */
    @StudentImplementationRequired
    public static DrawInstruction[] concatenate(DrawInstruction[] arr1, DrawInstruction[] arr2) {
        DrawInstruction[] newArr = new DrawInstruction[arr1.length + arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            newArr[i] = arr1[i];
        }

        for (int i = arr1.length; i < arr1.length+arr2.length; i++) {
            newArr[i] = arr2[i-arr1.length];
        }

        return newArr;
    }

    /**
     * This method creates a new array that is a copy of the input array arr, but with the element at the specified
     * index idx replaced by elem.
     *
     * @param arr the original array of type DrawInstruction
     * @param idx the index at which to replace the element
     * @param elem the new DrawInstruction to place at the specified index
     * @return A new array with the element at idx replaced by elem
     */
    @StudentImplementationRequired
    public static DrawInstruction[] replaceAtIndex(DrawInstruction[] arr, int idx, DrawInstruction elem) {
        DrawInstruction[] newArr = new DrawInstruction[arr.length];

        for (int i = 0; i < newArr.length; i++) {
            if(i == idx) {
                newArr[idx] = elem;
            } else {
                newArr[i] = arr[i];
            }
        }

        return newArr;
    }

    /**
     * Generates an array of DrawInstruction objects to draw a dragon curve of order n
     *
     * @param n The order of the dragon curve to generate
     * @return an array of DrawInstruction objects to draw a dragon curve of order n
     */
    @StudentImplementationRequired
    public static DrawInstruction[] dragonCurve(int n) {
        if(n <= 0) {
            return new DrawInstruction[]{
                DrawInstruction.DRAW_LINE
            };
        }
        if (n == 1) {
            return new DrawInstruction[]{
                DrawInstruction.DRAW_LINE,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.DRAW_LINE
            };
        }
        else {
            DrawInstruction[] lastOrder = dragonCurve(n - 1);
            DrawInstruction[] firstHalf = concatenate(lastOrder, new DrawInstruction[]{DrawInstruction.TURN_RIGHT});
            DrawInstruction[] secondHalf = replaceAtIndex(lastOrder, pow(2, n-1)-1, DrawInstruction.TURN_LEFT);
            return concatenate(firstHalf, secondHalf);
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
            return new DrawInstruction[]{
                DrawInstruction.DRAW_LINE,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.DRAW_LINE,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.TURN_RIGHT,
                DrawInstruction.DRAW_LINE,
            };
        } else {
            DrawInstruction[] lastOrder = kochSnowflake(n - 1);
            DrawInstruction[] currentOrder = new DrawInstruction[lastOrder.length * 4];
            int index = 0;

            for (DrawInstruction instruction : lastOrder) {
                if (instruction == DrawInstruction.DRAW_LINE) {
                    currentOrder[index++] = DrawInstruction.DRAW_LINE;
                    currentOrder[index++] = DrawInstruction.TURN_LEFT;
                    currentOrder[index++] = DrawInstruction.DRAW_LINE;
                    currentOrder[index++] = DrawInstruction.TURN_RIGHT;
                    currentOrder[index++] = DrawInstruction.TURN_RIGHT;
                    currentOrder[index++] = DrawInstruction.DRAW_LINE;
                    currentOrder[index++] = DrawInstruction.TURN_LEFT;
                    currentOrder[index++] = DrawInstruction.DRAW_LINE;
                } else if (instruction == DrawInstruction.TURN_LEFT) {
                    currentOrder[index++] = instruction;
                } else if (instruction == DrawInstruction.TURN_RIGHT) {
                    currentOrder[index++] = instruction;
                }
            }

            return currentOrder;
        }
    }
}
