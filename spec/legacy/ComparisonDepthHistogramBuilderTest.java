package spec.legacy;

import lib.expression.Expression;
import lib.legacy.ComparisonDepthHistogramBuilder;
import lib.legacy.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

abstract class ComparisonDepthHistogramBuilderTestBase<E> extends TestBase<E> {
    ComparisonDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsComparisonDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 5);
        expected.put(3, 1);

        assertEquals(expected,testSupport.v.comparisonDepthHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class ComparisonDepthHistogramBuilderTest extends ComparisonDepthHistogramBuilderTestBase<Expression> {
    ComparisonDepthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
