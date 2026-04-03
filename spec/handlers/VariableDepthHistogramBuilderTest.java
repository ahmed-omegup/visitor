package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.VariableDepthHistogramBuilder;

class VariableDepthHistogramBuilderTest {
    @Test
    void countsVariableReferencesPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 1);
        expected.put(2, 1);

        assertEquals(expected,sampleTraversalExpression().accept(v.variableDepthHistogramBuilder()));
    }
}