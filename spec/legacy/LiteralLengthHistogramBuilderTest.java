package spec.legacy;

import lib.expression.Expression;
import lib.legacy.HandlerFactory;
import lib.legacy.LiteralLengthHistogramBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

abstract class LiteralLengthHistogramBuilderTestBase<E> extends TestBase<E> {
    LiteralLengthHistogramBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void countsLiteralLengths() {
        var expected = new LinkedHashMap<Integer, Integer>();
        expected.put(2, 1);
        expected.put(1, 21);

        assertEquals(expected,testSupport.v.literalLengthHistogramBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class LiteralLengthHistogramBuilderTest extends LiteralLengthHistogramBuilderTestBase<Expression> {
    LiteralLengthHistogramBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
