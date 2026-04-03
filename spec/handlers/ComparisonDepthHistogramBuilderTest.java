package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonDepthHistogramBuilder;

class ComparisonDepthHistogramBuilderTest {
    @Test
    void countsComparisonDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 5);
        expected.put(3, 1);

        assertEquals(expected,sampleTraversalExpression().accept(v.comparisonDepthHistogramBuilder()));
    }
}