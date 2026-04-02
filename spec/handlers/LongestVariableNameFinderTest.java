package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.LongestVariableNameFinder;

class LongestVariableNameFinderTest {
    private final Factory factory = new Factory();
    @Test
    void findsLongestVariableName() {
        assertEquals(
            "threshold",
            new LongestVariableNameFinder().handle(factory.addition(factory.variableReference("threshold"), factory.literal("1")))
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestVariableNameFinder().handle(factory.addition(factory.variableReference("alpha"), factory.variableReference("bravo")))
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x", new LongestVariableNameFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}