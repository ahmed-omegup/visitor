package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import visitor.handlers.BooleanOperatorDepthHistogramBuilder;

class BooleanOperatorDepthHistogramBuilderTest {
    @Test
    void countsBooleanOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 2);

        assertEquals(expected, new BooleanOperatorDepthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}