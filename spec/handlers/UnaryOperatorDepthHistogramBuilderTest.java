package spec.handlers;

import lib.expression.Expression;
import lib.handlers.HandlerFactory;
import lib.handlers.UnaryOperatorDepthHistogramBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

abstract class UnaryOperatorDepthHistogramBuilderTestBase<E> extends TestBase<E> {
    UnaryOperatorDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsUnaryOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 2);

        assertEquals(expected,testSupport.v.unaryOperatorDepthHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class UnaryOperatorDepthHistogramBuilderTest extends UnaryOperatorDepthHistogramBuilderTestBase<Expression> {
    UnaryOperatorDepthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
