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
            new UnaryOperatorCounter().handle(lib.expression.Expression.addition(lib.expression.Expression.negation(lib.expression.Expression.literal("1")), lib.expression.Expression.logicalNot(lib.expression.Expression.literal("0"))))
        );
    }

    @Test
    void countsUnaryOperatorsAcrossTraversalExpression() {
        assertEquals(2, new UnaryOperatorCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}