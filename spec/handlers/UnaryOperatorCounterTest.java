package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Negation;
import lib.visitors.UnaryOperatorCounter;
import port.IFactory;

abstract class UnaryOperatorCounterTestBase<E extends Expression> extends TestBase<E> {
    UnaryOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsUnaryOperators() {
        assertEquals(
            2,
factory.addition(factory.negation(factory.literal("1")), factory.logicalNot(factory.literal("0"))).accept(testSupport.v.unaryOperatorCounter())
        );
    }

    @Test
    void countsUnaryOperatorsAcrossTraversalExpression() {
        assertEquals(2,testSupport.sampleTraversalExpression().accept(testSupport.v.unaryOperatorCounter()));
    }
}

class UnaryOperatorCounterTest extends UnaryOperatorCounterTestBase<Expression> {
    UnaryOperatorCounterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
