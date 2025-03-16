package h07.peano;


import h07.ConvertNumberToPeanoExpressionImpl;
import h07.NumberExpression;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Arrays;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents a factory for Peano number expressions.
 */
public class PeanoNumberExpressionFactory {
    /**
     * Converts an array of number expressions to an array of Peano number expressions.
     *
     * @param numberExpressions the number expressions to convert
     * @return the converted Peano number expressions
     */
    @StudentImplementationRequired
    public static PeanoNumberExpression[] fromNumberExpressions(NumberExpression[] numberExpressions) {
        return Arrays.stream(numberExpressions)
            .map(x -> new ConvertNumberToPeanoExpressionImpl().convert(x))
            .toArray(PeanoNumberExpression[]::new);
    }

    /**
     * Folds an array of Peano number expressions into a single Peano number expression.
     *
     * @param peanoNumberExpressions the Peano number expressions to fold
     * @param initial the initial Peano number expression
     * @param operation the operation to apply
     * @return the folded Peano number expression
     */
    @StudentImplementationRequired
    public static PeanoNumberExpression fold(PeanoNumberExpression[] peanoNumberExpressions, PeanoNumberExpression initial, PeanoArithmeticExpression operation) {
        return Arrays.stream(peanoNumberExpressions).reduce(initial, (i,a) -> operation.evaluate(i,a));
    }
}
