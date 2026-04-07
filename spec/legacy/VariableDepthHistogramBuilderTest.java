package spec.legacy;

import lib.expression.Expression;
import lib.legacy.HandlerFactory;
import lib.legacy.VariableDepthHistogramBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

abstract class VariableDepthHistogramBuilderTestBase<E> extends TestBase<E> {
    VariableDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsVariableReferencesPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 1);
        expected.put(2, 1);

        assertEquals(expected,testSupport.v.variableDepthHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class VariableDepthHistogramBuilderTest extends VariableDepthHistogramBuilderTestBase<Expression> {
    VariableDepthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
