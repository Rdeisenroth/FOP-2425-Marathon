package h07;

import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Represents an arithmetic operation on numbers.
 */
@StudentCreationRequired
public interface ArithmeticExpression {
    /**
     * Applies the arithmetic operation to the two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the result of the operation
     */
    NumberExpression evaluate(NumberExpression num1, NumberExpression num2);
}
