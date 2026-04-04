package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.UnaryOperatorDepthHistogramBuilder;

abstract class UnaryOperatorDepthHistogramBuilderTestBase<E extends Expression> extends TestBase<E> {
    UnaryOperatorDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsUnaryOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 2);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.unaryOperatorDepthHistogramBuilder()));
    }
}

class UnaryOperatorDepthHistogramBuilderTest extends UnaryOperatorDepthHistogramBuilderTestBase<Expression> {
    UnaryOperatorDepthHistogramBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
