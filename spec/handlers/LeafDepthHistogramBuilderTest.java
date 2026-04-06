package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.handlers.LeafDepthHistogramBuilder;

abstract class LeafDepthHistogramBuilderTestBase<E> extends TestBase<E> {
    LeafDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsLeavesPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 1);
        expected.put(2, 1);

        assertEquals(
            expected,testSupport.v.leafDepthHistogramBuilder().apply(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void countsTraversalExpressionLeafDepths() {
        assertEquals(Map.of(2, 1, 3, 17, 4, 6),testSupport.v.leafDepthHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class LeafDepthHistogramBuilderTest extends LeafDepthHistogramBuilderTestBase<Expression> {
    LeafDepthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
