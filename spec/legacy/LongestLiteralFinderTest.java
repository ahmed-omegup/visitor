package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.LongestLiteralFinder;

abstract class LongestLiteralFinderTestBase<E> extends TestBase<E> {
    LongestLiteralFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void findsLongestLiteralValue() {
        assertEquals(
            "12345",testSupport.v.longestLiteralFinder().apply(factory.addition(factory.literal("12345"), factory.variableReference("x")))
        );
    }

    @Test
    void keepsLeftValueOnEqualLengthTie() {
        assertEquals(
            "alpha",testSupport.v.longestLiteralFinder().apply(factory.functionCall(factory.variableReference("f"), of( factory.literal("alpha"), factory.literal("bravo"))))
        );
    }

    @Test
    void findsLongestLiteralAcrossTraversalExpression() {
        assertEquals("10",testSupport.v.longestLiteralFinder().apply(testSupport.sampleTraversalExpression()));
    }
}

class LongestLiteralFinderTest extends LongestLiteralFinderTestBase<Expression> {
    LongestLiteralFinderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
