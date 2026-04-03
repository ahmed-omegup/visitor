package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.handlers.LiteralDepthHistogramBuilder;
import port.IFactory;

class LiteralDepthHistogramBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void countsLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 1);

        assertEquals(expected,factory.addition(factory.literal("1"), factory.negation(factory.literal("2"))).accept(TestSupport.handlers().literalDepthHistogramBuilder()));
    }

    @Test
    void countsTraversalExpressionLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 16);
        expected.put(4, 6);

        assertEquals(expected,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().literalDepthHistogramBuilder()));
    }
}