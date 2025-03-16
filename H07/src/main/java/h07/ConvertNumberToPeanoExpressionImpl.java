package h07;

import h07.peano.PeanoNumberExpression;
import h07.peano.Successor;
import h07.peano.Zero;

public class ConvertNumberToPeanoExpressionImpl implements ConvertNumberToPeanoExpression {
    @Override
    public PeanoNumberExpression convert(NumberExpression e) {
        var val = e.evaluate();
        return val == 0 ? Zero::new : () -> new Successor(new ConvertNumberToPeanoExpressionImpl().convert(() -> val - 1).evaluate());
    }
}
