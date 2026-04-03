package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.visitors.LiteralDepthHistogramBuilder;
import port.IFactory;

class LiteralDepthHistogramBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void countsLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 1);

        assertEquals(expected,factory.addition(factory.literal("1"), factory.negation(factory.literal("2"))).accept(v.literalDepthHistogramBuilder()));
    }

    @Test
    void countsTraversalExpressionLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 16);
        expected.put(4, 6);

        assertEquals(expected,sampleTraversalExpression().accept(v.literalDepthHistogramBuilder()));
    }
}