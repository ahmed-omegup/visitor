package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.VariableNameLengthHistogramBuilder;

abstract class VariableNameLengthHistogramBuilderTestBase<E> extends TestBase<E> {
    VariableNameLengthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsVariableNameLengths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 2);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.variableNameLengthHistogramBuilder()));
    }
}

class VariableNameLengthHistogramBuilderTest extends VariableNameLengthHistogramBuilderTestBase<Expression> {
    VariableNameLengthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
