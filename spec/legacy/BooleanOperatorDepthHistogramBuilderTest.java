package spec.legacy;

import lib.expression.Expression;
import lib.legacy.BooleanOperatorDepthHistogramBuilder;
import lib.legacy.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

abstract class BooleanOperatorDepthHistogramBuilderTestBase<E> extends TestBase<E> {
    BooleanOperatorDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsBooleanOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 2);

        assertEquals(expected,testSupport.v.booleanOperatorDepthHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class BooleanOperatorDepthHistogramBuilderTest extends BooleanOperatorDepthHistogramBuilderTestBase<Expression> {
    BooleanOperatorDepthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
