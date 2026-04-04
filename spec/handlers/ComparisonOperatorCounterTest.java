package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonOperatorCounter;

abstract class ComparisonOperatorCounterTestBase<E extends Expression> extends TestBase<E> {
    ComparisonOperatorCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsComparisonOperatorsInTraversalExpression() {
        assertEquals(6,testSupport.sampleTraversalExpression().accept(testSupport.v.comparisonOperatorCounter()));
    }
}

class ComparisonOperatorCounterTest extends ComparisonOperatorCounterTestBase<Expression> {
    ComparisonOperatorCounterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
