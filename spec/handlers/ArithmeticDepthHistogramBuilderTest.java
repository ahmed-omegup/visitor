package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.ArithmeticDepthHistogramBuilder;

class ArithmeticDepthHistogramBuilderTest {
    @Test
    void countsArithmeticOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 4);
        expected.put(3, 2);

        assertEquals(expected,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().arithmeticDepthHistogramBuilder()));
    }
}