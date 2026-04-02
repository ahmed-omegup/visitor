package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Negation;
import visitor.handlers.UnaryOperatorCounter;

class UnaryOperatorCounterTest {
    @Test
    void countsUnaryOperators() {
        assertEquals(
            2,
            new UnaryOperatorCounter().handle(new Addition(new Negation(new Literal("1")), new LogicalNot(new Literal("0"))))
        );
    }

    @Test
    void countsUnaryOperatorsAcrossTraversalExpression() {
        assertEquals(2, new UnaryOperatorCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}