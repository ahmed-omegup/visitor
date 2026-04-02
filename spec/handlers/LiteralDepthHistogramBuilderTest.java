package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.handlers.LiteralDepthHistogramBuilder;

class LiteralDepthHistogramBuilderTest {
    @Test
    void countsLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 1);

        assertEquals(expected, new LiteralDepthHistogramBuilder().handle(addition(literal("1"), negation(literal("2")))));
    }

    @Test
    void countsTraversalExpressionLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 16);
        expected.put(4, 6);

        assertEquals(expected, new LiteralDepthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}