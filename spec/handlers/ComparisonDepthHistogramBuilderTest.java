package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.handlers.ComparisonDepthHistogramBuilder;

class ComparisonDepthHistogramBuilderTest {
    @Test
    void countsComparisonDepths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 5);
        expected.put(3, 1);

        assertEquals(expected,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().comparisonDepthHistogramBuilder()));
    }
}