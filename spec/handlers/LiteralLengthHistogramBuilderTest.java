package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import lib.visitors.LiteralLengthHistogramBuilder;

abstract class LiteralLengthHistogramBuilderTestBase<E extends Expression> extends TestBase<E> {
    LiteralLengthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsLiteralLengths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 1);
        expected.put(1, 21);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.literalLengthHistogramBuilder()));
    }
}

class LiteralLengthHistogramBuilderTest extends LiteralLengthHistogramBuilderTestBase<Expression> {
    LiteralLengthHistogramBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
