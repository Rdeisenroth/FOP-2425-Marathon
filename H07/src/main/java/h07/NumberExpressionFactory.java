package h07;

import java.util.function.IntPredicate;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A factory class for creating number expressions.
 */
public class NumberExpressionFactory {
    /**
     * Calculates the product of all possible pairs of numbers in the given array.
     *
     * @param numbers the array of number expressions to calculate the multiplication table
     * @return An array of number expressions representing the result of the
     *         multiplication table of the given numbers.
     */
//    @StudentImplementationRequired
//    public static NumberExpression[] multiplicationTable(NumberExpression[] numbers) {
//        return crash(); // TODO: H2.1 - remove if implemented
//    }


    // TODO: H2.2 - uncomment for testing
    /**
     * Calculates the product of all possible pairs of numbers in the given range.
     *
     * @param lowerBound the lower bound of the multiplication table, inclusive
     * @param upperBound the upper bound of the multiplication table, inclusive
     * @return An array of number expressions representing the result of the
     *         multiplication table of the numbers from lowerBound to upperBound.
     */
//    @DoNotTouch
//    public static NumberExpression[] multiplicationTable(int lowerBound, int upperBound) {
//        int numberOfNumbers = upperBound - lowerBound + 1;
//        NumberExpression[] baseNumbers = new NumberExpression[numberOfNumbers];
//
//        for (int i = lowerBound; i <= upperBound; i++) {
//            // Copy to local variable to make it effectively final, so it can be used in
//            // lambda
//            int finalI = i;
//            baseNumbers[i - lowerBound] = () -> finalI;
//        }
//
//        return multiplicationTable(baseNumbers);
//    }

    /**
     * Filters the given array of number expressions based on the given predicate.
     * The returned array should contain only the number expressions that satisfy
     * the predicate in the same order as they appear in the input array.
     * This means there should be no null values in the returned array.
     *
     * @param numbers   the array of number expressions to filter
     * @param predicate the predicate to filter the number expressions
     * @return An array of number expressions that satisfy the predicate.
     */
//    @StudentImplementationRequired
//    public static NumberExpression[] filter(NumberExpression[] numbers, IntPredicate predicate) {
//        return crash(); // TODO: H4.4 - remove if implemented
//    }
}
