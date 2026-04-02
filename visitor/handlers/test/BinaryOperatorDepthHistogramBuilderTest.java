package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import visitor.handlers.BinaryOperatorDepthHistogramBuilder;

class BinaryOperatorDepthHistogramBuilderTest {
    @Test
    void countsBinaryOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 2);
        expected.put(2, 9);
        expected.put(3, 3);

        assertEquals(expected, new BinaryOperatorDepthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}