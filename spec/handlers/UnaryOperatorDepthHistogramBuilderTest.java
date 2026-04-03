package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.UnaryOperatorDepthHistogramBuilder;

class UnaryOperatorDepthHistogramBuilderTest {
    @Test
    void countsUnaryOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 2);

        assertEquals(expected,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().unaryOperatorDepthHistogramBuilder()));
    }
}