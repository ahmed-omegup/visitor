package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.LongestLiteralFinder;
import port.IFactory;

abstract class LongestLiteralFinderTestBase<E extends Expression> extends TestBase<E> {
    LongestLiteralFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void findsLongestLiteralValue() {
        assertEquals(
            "12345",
factory.addition(factory.literal("12345"), factory.variableReference("x")).accept(testSupport.v.longestLiteralFinder())
        );
    }

    @Test
    void keepsLeftValueOnEqualLengthTie() {
        assertEquals(
            "alpha",
factory.functionCall(factory.variableReference("f"), of( factory.literal("alpha"), factory.literal("bravo"))).accept(testSupport.v.longestLiteralFinder())
        );
    }

    @Test
    void findsLongestLiteralAcrossTraversalExpression() {
        assertEquals("10",testSupport.sampleTraversalExpression().accept(testSupport.v.longestLiteralFinder()));
    }
}

class LongestLiteralFinderTest extends LongestLiteralFinderTestBase<Expression> {
    LongestLiteralFinderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
