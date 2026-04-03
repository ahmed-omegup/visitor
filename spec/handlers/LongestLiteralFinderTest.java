package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.LongestLiteralFinder;
import port.IFactory;

class LongestLiteralFinderTest {
    private final IFactory factory = new Factory();
    @Test
    void findsLongestLiteralValue() {
        assertEquals(
            "12345",
factory.addition(factory.literal("12345"), factory.variableReference("x")).accept(v.longestLiteralFinder())
        );
    }

    @Test
    void keepsLeftValueOnEqualLengthTie() {
        assertEquals(
            "alpha",
factory.functionCall(factory.variableReference("f"), factory.literal("alpha"), factory.literal("bravo")).accept(v.longestLiteralFinder())
        );
    }

    @Test
    void findsLongestLiteralAcrossTraversalExpression() {
        assertEquals("10",sampleTraversalExpression().accept(v.longestLiteralFinder()));
    }
}