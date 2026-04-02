package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import visitor.handlers.VariableDepthHistogramBuilder;

class VariableDepthHistogramBuilderTest {
    @Test
    void countsVariableReferencesPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 1);
        expected.put(2, 1);

        assertEquals(expected, new VariableDepthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}