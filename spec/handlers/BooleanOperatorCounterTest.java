package spec.handlers;

import lib.expression.Expression;
import lib.handlers.BooleanOperatorCounter;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class BooleanOperatorCounterTestBase<E> extends TestBase<E> {
    BooleanOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsBooleanOperatorsInTraversalExpression() {
        assertEquals(3,testSupport.v.booleanOperatorCounter().apply(testSupport.sampleTraversalExpression()));
    }
}

class BooleanOperatorCounterTest extends BooleanOperatorCounterTestBase<Expression> {
    BooleanOperatorCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
