package h07;

import h07.peano.*;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        testHeader("Multiplication Table");
        numberExpressionMultiplicationTableTests();

        testHeader("Peano Number Expressions");
        peanoNumberExpressionTests();

        testHeader("Filter, Fold, Map");
        filterFoldMapTests();
    }

    @DoNotTouch
    private static void testHeader(String testName) {
        System.out.println("-----------------------------------");
        System.out.println("Running test: " + testName);
        System.out.println("-----------------------------------");
    }

    @DoNotTouch
    private static void numberExpressionMultiplicationTableTests() {
        int lowerBound = 1;
        int upperBound = 10;
        NumberExpression[] multiplicationTable = NumberExpressionFactory.multiplicationTable(lowerBound, upperBound);

        for (int i = lowerBound; i <= upperBound; i++) {
            for (int j = lowerBound; j <= upperBound; j++) {
                System.out.printf("| %4s ", multiplicationTable[(i - lowerBound) * (upperBound - lowerBound + 1) + (j - lowerBound)].evaluate());
            }
            System.out.println("|");
        }
    }

    private static final NaturalNumber THREE = new Successor(new Successor(new Successor(new Zero())));
    private static final NaturalNumber SEVEN = new Successor(new Successor(new Successor(new Successor(new Successor(new Successor(new Successor(new Zero())))))));

    @StudentImplementationRequired
    private static void peanoNumberExpressionTests() {
        PeanoNumberExpression three = () -> THREE;
        PeanoNumberExpression seven = () -> SEVEN;

        PeanoNumberExpression sum = new PeanoAddExpression().evaluate(three, seven);
        PeanoNumberExpression product = new PeanoMultiplyExpression().evaluate(three, seven);

        System.out.println(sum.evaluate());
        System.out.println(product.evaluate());

        // Not required but can be used to convert Peano numbers to integers
        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(sum).evaluate());
        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(product).evaluate());
    }

    @StudentImplementationRequired
    private static void filterFoldMapTests() {
        NumberExpression[] numbers = NumberExpressionFactory.multiplicationTable(1, 10);
        NumberExpression[] filteredNumbers = NumberExpressionFactory.filter(numbers, n -> n % 3 == 0);
        PeanoNumberExpression[] filteredPeanoNumbers = PeanoNumberExpressionFactory
                .fromNumberExpressions(filteredNumbers);
        PeanoNumberExpression foldedPeanoNumbers = PeanoNumberExpressionFactory.fold(filteredPeanoNumbers, Zero::new,
                new PeanoAddExpression());
        int foldedNumber = new ConvertPeanoToNumberExpressionImpl().convert(foldedPeanoNumbers).evaluate();
        System.out.println(foldedNumber);
    }
}
