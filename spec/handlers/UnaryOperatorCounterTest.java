package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Negation;
import lib.visitors.UnaryOperatorCounter;
import port.IFactory;

class UnaryOperatorCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsUnaryOperators() {
        assertEquals(
            2,
factory.addition(factory.negation(factory.literal("1")), factory.logicalNot(factory.literal("0"))).accept(v.unaryOperatorCounter())
        );
    }

    @Test
    void countsUnaryOperatorsAcrossTraversalExpression() {
        assertEquals(2,sampleTraversalExpression().accept(v.unaryOperatorCounter()));
    }
}