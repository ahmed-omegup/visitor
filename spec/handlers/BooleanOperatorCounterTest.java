package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.BooleanOperatorCounter;

abstract class BooleanOperatorCounterTestBase<E> extends TestBase<E> {
    BooleanOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsBooleanOperatorsInTraversalExpression() {
        assertEquals(3,testSupport.sampleTraversalExpression().accept(testSupport.v.booleanOperatorCounter()));
    }
}

class BooleanOperatorCounterTest extends BooleanOperatorCounterTestBase<Expression> {
    BooleanOperatorCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
