package h06.problems;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * A class containing different implementations for computing the nth number in the Fibonacci sequence.
 *
 * @author Manuel Peters
 */
public class Fibonacci {

    /**
     * Default Constructor for this class.
     */
    @DoNotTouch
    public Fibonacci() {
    }

    /**
     * Computes the nth number from the Fibonacci sequence recursively.
     *
     * @param n The index of the Fibonacci sequence to compute.
     * @return The nth number from the Fibonacci sequence.
     */
    @DoNotTouch
    public static int fibonacciRecursiveClassic(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciRecursiveClassic(n - 1) + fibonacciRecursiveClassic(n - 2);
        }
    }

    /**
     * Computes the nth number from the Fibonacci sequence using a different recursive approach.
     *
     * @param n The index of the Fibonacci sequence to compute.
     * @return The nth number from the Fibonacci sequence.
     */
    @StudentImplementationRequired
    public static int fibonacciRecursiveDifferent(int n) {
        return doTheRecursion(0, 1, n);
    }

    @StudentImplementationRequired
    private static int doTheRecursion(int a, int b, int n) {
        return (n <= 0) ? a : doTheRecursion(b, a + b, n - 1);
    }

    /**
     * Computes the nth number from the Fibonacci sequence iteratively.
     *
     * @param n The index of the Fibonacci sequence to compute.
     * @return The nth number from the Fibonacci sequence.
     */
    @StudentImplementationRequired
    public static int fibonacciIterative(int n) {
        int a = 0;
        int b = 1;
        if (n < 2) {
            return n;
        }
        for (int i = 2; i <= n; i++) {
            final int next = b + a;
            a = b;
            b = next;
        }
        return b;
    }
}
