package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Negation;
import lib.handlers.UnaryOperatorCounter;

class UnaryOperatorCounterTest {
    @Test
    void countsUnaryOperators() {
        assertEquals(
            2,
            new UnaryOperatorCounter().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("1")), lib.expression.ExpressionFactory.logicalNot(lib.expression.ExpressionFactory.literal("0"))))
        );
    }

    @Test
    void countsUnaryOperatorsAcrossTraversalExpression() {
        assertEquals(2, new UnaryOperatorCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}