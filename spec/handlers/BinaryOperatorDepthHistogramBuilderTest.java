package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryOperatorDepthHistogramBuilder;

class BinaryOperatorDepthHistogramBuilderTest {
    @Test
    void countsBinaryOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 2);
        expected.put(2, 9);
        expected.put(3, 3);

        assertEquals(expected,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().binaryOperatorDepthHistogramBuilder()));
    }
}