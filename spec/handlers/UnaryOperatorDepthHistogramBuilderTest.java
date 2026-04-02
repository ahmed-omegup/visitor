package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.handlers.UnaryOperatorDepthHistogramBuilder;

class UnaryOperatorDepthHistogramBuilderTest {
    @Test
    void countsUnaryOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 2);

        assertEquals(expected, new UnaryOperatorDepthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}