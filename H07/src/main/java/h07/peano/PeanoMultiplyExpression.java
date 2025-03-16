package h07.peano;

public class PeanoMultiplyExpression implements PeanoArithmeticExpression {
    @Override
    public PeanoNumberExpression evaluate(PeanoNumberExpression e1, PeanoNumberExpression e2) {
        return e2.evaluate() instanceof final Successor e2vs ? () -> new PeanoAddExpression().evaluate(
            e1,
            new PeanoMultiplyExpression().evaluate(
                e1, () -> e2vs.predecessor
            )
        ).evaluate() : e2;
    }
}
