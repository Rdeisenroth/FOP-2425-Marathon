package h07;

import h07.peano.PeanoNumberExpression;
import h07.peano.Successor;

public class ConvertPeanoToNumberExpressionImpl implements ConvertPeanoToNumberExpression {
    @Override
    public NumberExpression convert(PeanoNumberExpression e) {
        var val = e.evaluate();
        return val instanceof Successor sval
               ? () -> new ConvertPeanoToNumberExpressionImpl().convert(() -> sval.predecessor).evaluate() + 1
               : () -> 0;
    }
}
