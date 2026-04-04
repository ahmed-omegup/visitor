package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.visitors.LiteralDepthHistogramBuilder;

abstract class LiteralDepthHistogramBuilderTestBase<E extends Expression> extends TestBase<E> {
    LiteralDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 1);

        assertEquals(expected,factory.addition(factory.literal("1"), factory.negation(factory.literal("2"))).accept(testSupport.v.literalDepthHistogramBuilder()));
    }

    @Test
    void countsTraversalExpressionLiteralsPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 16);
        expected.put(4, 6);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.literalDepthHistogramBuilder()));
    }
}

class LiteralDepthHistogramBuilderTest extends LiteralDepthHistogramBuilderTestBase<Expression> {
    LiteralDepthHistogramBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
