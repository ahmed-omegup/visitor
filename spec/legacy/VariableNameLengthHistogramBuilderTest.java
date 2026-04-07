package spec.legacy;

import lib.expression.Expression;
import lib.legacy.HandlerFactory;
import lib.legacy.VariableNameLengthHistogramBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

abstract class VariableNameLengthHistogramBuilderTestBase<E> extends TestBase<E> {
    VariableNameLengthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsVariableNameLengths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(1, 2);

        assertEquals(expected,testSupport.v.variableNameLengthHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class VariableNameLengthHistogramBuilderTest extends VariableNameLengthHistogramBuilderTestBase<Expression> {
    VariableNameLengthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
