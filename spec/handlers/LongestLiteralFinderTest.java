package spec.handlers;

import static lib.expression.Factory.*;

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
            new LongestLiteralFinder().handle(addition(literal("12345"), variableReference("x")))
        );
    }

    @Test
    void keepsLeftValueOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestLiteralFinder().handle(functionCall(variableReference("f"), literal("alpha"), literal("bravo")))
        );
    }

    @Test
    void findsLongestLiteralAcrossTraversalExpression() {
        assertEquals("10", new LongestLiteralFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}