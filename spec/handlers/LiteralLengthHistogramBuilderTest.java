package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.LiteralLengthHistogramBuilder;

class LiteralLengthHistogramBuilderTest {
    @Test
    void countsLiteralLengths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 1);
        expected.put(1, 21);

        assertEquals(expected,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().literalLengthHistogramBuilder()));
    }
}