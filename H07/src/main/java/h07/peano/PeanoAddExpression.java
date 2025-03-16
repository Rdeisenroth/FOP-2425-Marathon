package h07.peano;

public class PeanoAddExpression implements PeanoArithmeticExpression {
    @Override
    public PeanoNumberExpression evaluate(PeanoNumberExpression e1, PeanoNumberExpression e2) {
        return e2.evaluate() instanceof final Successor e2vs ? () -> {
            return new Successor(
                new PeanoAddExpression().evaluate(
                    e1, () -> {
                        return e2vs.predecessor;
                    }
                ).evaluate()
            );
        } : e1;
    }
}
