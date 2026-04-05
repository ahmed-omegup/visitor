package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonOperatorCounter;

abstract class ComparisonOperatorCounterTestBase<E> extends TestBase<E> {
    ComparisonOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsComparisonOperatorsInTraversalExpression() {
        assertEquals(6,testSupport.v.comparisonOperatorCounter().apply(testSupport.sampleTraversalExpression()));
    }
}

class ComparisonOperatorCounterTest extends ComparisonOperatorCounterTestBase<Expression> {
    ComparisonOperatorCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
