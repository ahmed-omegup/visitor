package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.VariableDepthHistogramBuilder;

abstract class VariableDepthHistogramBuilderTestBase<E> extends TestBase<E> {
    VariableDepthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsVariableReferencesPerDepth() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(3, 1);
        expected.put(2, 1);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.variableDepthHistogramBuilder()));
    }
}

class VariableDepthHistogramBuilderTest extends VariableDepthHistogramBuilderTestBase<Expression> {
    VariableDepthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
