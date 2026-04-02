package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.handlers.VariableNameLengthHistogramBuilder;

class VariableNameLengthHistogramBuilderTest {
    @Test
    void countsVariableNameLengths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 2);

        assertEquals(expected, new VariableNameLengthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}