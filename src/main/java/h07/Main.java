package h07;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import h07.peano.NaturalNumber;
import h07.peano.Successor;
import h07.peano.Zero;

import static org.tudalgo.algoutils.student.Student.crash;

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
        // TODO: H2.2 - uncomment to test
//        int lowerBound = 1;
//        int upperBound = 10;
//        NumberExpression[] multiplicationTable = NumberExpressionFactory.multiplicationTable(lowerBound, upperBound);
//
//        for (int i = lowerBound; i <= upperBound; i++) {
//            for (int j = lowerBound; j <= upperBound; j++) {
//                System.out.printf("| %4s ", multiplicationTable[(i - lowerBound) * (upperBound - lowerBound + 1) + (j - lowerBound)].evaluate());
//            }
//            System.out.println("|");
//        }
    }

    private static final NaturalNumber THREE = new Successor(new Successor(new Successor(new Zero())));
    private static final NaturalNumber SEVEN = new Successor(new Successor(new Successor(new Successor(new Successor(new Successor(new Successor(new Zero())))))));

    @StudentImplementationRequired
    private static void peanoNumberExpressionTests() {
        crash(); // TODO: H3.3 - remove if implemented
    }

    @StudentImplementationRequired
    private static void filterFoldMapTests() {
        crash(); // TODO: H4.6 - remove if implemented
    }
}
