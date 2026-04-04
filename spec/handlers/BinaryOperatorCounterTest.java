package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryOperatorCounter;

abstract class BinaryOperatorCounterTestBase<E extends Expression> extends TestBase<E> {
    BinaryOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsBinaryOperatorsInTraversalExpression() {
        assertEquals(14,testSupport.sampleTraversalExpression().accept(testSupport.v.binaryOperatorCounter()));
    }
}

class BinaryOperatorCounterTest extends BinaryOperatorCounterTestBase<Expression> {
    BinaryOperatorCounterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
