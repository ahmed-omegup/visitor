package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryOperatorDepthHistogramBuilder;

abstract class BinaryOperatorDepthHistogramBuilderTestBase<E> extends TestBase<E> {
    BinaryOperatorDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsBinaryOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 2);
        expected.put(2, 9);
        expected.put(3, 3);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.binaryOperatorDepthHistogramBuilder()));
    }
}

class BinaryOperatorDepthHistogramBuilderTest extends BinaryOperatorDepthHistogramBuilderTestBase<Expression> {
    BinaryOperatorDepthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
