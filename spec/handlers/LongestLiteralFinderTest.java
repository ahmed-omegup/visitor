package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.LongestLiteralFinder;

class LongestLiteralFinderTest {
    private final Factory factory = new Factory();
    @Test
    void findsLongestLiteralValue() {
        assertEquals(
            "12345",
            new LongestLiteralFinder().handle(factory.addition(factory.literal("12345"), factory.variableReference("x")))
        );
    }

    @Test
    void keepsLeftValueOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestLiteralFinder().handle(factory.functionCall(factory.variableReference("f"), factory.literal("alpha"), factory.literal("bravo")))
        );
    }

    @Test
    void findsLongestLiteralAcrossTraversalExpression() {
        assertEquals("10", new LongestLiteralFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}