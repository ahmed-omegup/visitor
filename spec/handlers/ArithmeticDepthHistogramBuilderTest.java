package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Expression;
import lib.visitors.ArithmeticDepthHistogramBuilder;
import static spec.handlers.TestSupport.*;

abstract class ArithmeticDepthHistogramBuilderTestBase<E> {
    protected final TestSupport<E> testSupport;

    ArithmeticDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        this.testSupport = testSupport;
    }

    @Test
    void countsArithmeticOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 4);
        expected.put(3, 2);

        assertEquals(expected, testSupport.sampleTraversalExpression()
                .accept(testSupport.v.arithmeticDepthHistogramBuilder()));
    }
}

class ArithmeticDepthHistogramBuilderTest extends ArithmeticDepthHistogramBuilderTestBase<Expression> {
    ArithmeticDepthHistogramBuilderTest() {
        super(TestSupport.instance);
    }
}