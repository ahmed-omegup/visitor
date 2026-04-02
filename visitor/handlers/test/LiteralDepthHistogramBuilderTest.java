package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.handlers.LiteralDepthHistogramBuilder;

class LiteralDepthHistogramBuilderTest {
    @Test
    void countsLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 1);

        assertEquals(expected, new LiteralDepthHistogramBuilder().handle(new Addition(new Literal("1"), new Negation(new Literal("2")))));
    }

    @Test
    void countsTraversalExpressionLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 16);
        expected.put(4, 6);

        assertEquals(expected, new LiteralDepthHistogramBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}