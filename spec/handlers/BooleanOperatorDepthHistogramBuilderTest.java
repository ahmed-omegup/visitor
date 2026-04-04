package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.BooleanOperatorDepthHistogramBuilder;

abstract class BooleanOperatorDepthHistogramBuilderTestBase<E extends Expression> extends TestBase<E> {
    BooleanOperatorDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsBooleanOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 2);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.booleanOperatorDepthHistogramBuilder()));
    }
}

class BooleanOperatorDepthHistogramBuilderTest extends BooleanOperatorDepthHistogramBuilderTestBase<Expression> {
    BooleanOperatorDepthHistogramBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
