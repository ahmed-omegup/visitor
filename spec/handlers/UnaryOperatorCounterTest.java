package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Negation;
import lib.handlers.UnaryOperatorCounter;
import port.IFactory;

class UnaryOperatorCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsUnaryOperators() {
        assertEquals(
            2,
factory.addition(factory.negation(factory.literal("1")), factory.logicalNot(factory.literal("0"))).accept(TestSupport.handlers().unaryOperatorCounter())
        );
    }

    @Test
    void countsUnaryOperatorsAcrossTraversalExpression() {
        assertEquals(2,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().unaryOperatorCounter()));
    }
}