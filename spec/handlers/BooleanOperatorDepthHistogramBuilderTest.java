package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.BooleanOperatorDepthHistogramBuilder;

class BooleanOperatorDepthHistogramBuilderTest {
    @Test
    void countsBooleanOperatorDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 2);

        assertEquals(expected,sampleTraversalExpression().accept(v.booleanOperatorDepthHistogramBuilder()));
    }
}