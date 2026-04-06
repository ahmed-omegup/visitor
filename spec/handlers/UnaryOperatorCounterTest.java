package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Negation;
import lib.handlers.HandlerFactory;
import lib.handlers.UnaryOperatorCounter;

abstract class UnaryOperatorCounterTestBase<E> extends TestBase<E> {
    UnaryOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsUnaryOperators() {
        assertEquals(
            2,testSupport.v.unaryOperatorCounter().apply(factory.addition(factory.negation(factory.literal("1")), factory.logicalNot(factory.literal("0"))))
        );
    }

    @Test
    void countsUnaryOperatorsAcrossTraversalExpression() {
        assertEquals(2,testSupport.v.unaryOperatorCounter().apply(testSupport.sampleTraversalExpression()));
    }
}

class UnaryOperatorCounterTest extends UnaryOperatorCounterTestBase<Expression> {
    UnaryOperatorCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
