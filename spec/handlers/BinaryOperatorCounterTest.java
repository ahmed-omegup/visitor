package spec.handlers;

import lib.expression.Expression;
import lib.handlers.BinaryOperatorCounter;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class BinaryOperatorCounterTestBase<E> extends TestBase<E> {
    BinaryOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsBinaryOperatorsInTraversalExpression() {
        assertEquals(14,testSupport.v.binaryOperatorCounter().apply(testSupport.sampleTraversalExpression()));
    }
}

class BinaryOperatorCounterTest extends BinaryOperatorCounterTestBase<Expression> {
    BinaryOperatorCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
