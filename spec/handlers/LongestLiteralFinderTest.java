package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.LongestLiteralFinder;

class LongestLiteralFinderTest {
    @Test
    void findsLongestLiteralValue() {
        assertEquals(
            "12345",
            new LongestLiteralFinder().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("12345"), lib.expression.ExpressionFactory.variableReference("x")))
        );
    }

    @Test
    void keepsLeftValueOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestLiteralFinder().handle(lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("f"), lib.expression.ExpressionFactory.literal("alpha"), lib.expression.ExpressionFactory.literal("bravo")))
        );
    }

    @Test
    void findsLongestLiteralAcrossTraversalExpression() {
        assertEquals("10", new LongestLiteralFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}