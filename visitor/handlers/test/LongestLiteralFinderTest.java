package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.LongestLiteralFinder;

class LongestLiteralFinderTest {
    @Test
    void findsLongestLiteralValue() {
        assertEquals(
            "12345",
            new LongestLiteralFinder().handle(new Addition(new Literal("12345"), new VariableReference("x")))
        );
    }

    @Test
    void keepsLeftValueOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestLiteralFinder().handle(new FunctionCall(new VariableReference("f"), new Literal("alpha"), new Literal("bravo")))
        );
    }

    @Test
    void findsLongestLiteralAcrossTraversalExpression() {
        assertEquals("10", new LongestLiteralFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}