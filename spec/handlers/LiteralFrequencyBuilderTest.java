package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;
import lib.visitors.LiteralFrequencyBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import port.IFactory;

abstract class LiteralFrequencyBuilderTestBase<E extends Expression> extends TestBase<E> {
    LiteralFrequencyBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsLiteralOccurrences() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("10", 1);
        expected.put("1", 3);
        expected.put("0", 2);
        expected.put("7", 2);
        expected.put("2", 5);
        expected.put("8", 1);
        expected.put("9", 1);
        expected.put("4", 2);
        expected.put("3", 3);
        expected.put("5", 1);
        expected.put("6", 1);

        assertEquals(expected,testSupport.sampleTraversalExpression().accept(testSupport.v.literalFrequencyBuilder()));
    }

    @Test
    void returnsEmptyMapWhenNoLiteralsExist() {
        assertEquals(
            new LinkedHashMap<String, Integer>(),
factory.variableReference("x").accept(testSupport.v.literalFrequencyBuilder())
        );
    }
}

class LiteralFrequencyBuilderTest extends LiteralFrequencyBuilderTestBase<Expression> {
    LiteralFrequencyBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
