package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.LongestVariableNameFinder;

class LongestVariableNameFinderTest {
    @Test
    void findsLongestVariableName() {
        assertEquals(
            "threshold",
            new LongestVariableNameFinder().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("threshold"), lib.expression.Expression.literal("1")))
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestVariableNameFinder().handle(lib.expression.Expression.addition(lib.expression.Expression.variableReference("alpha"), lib.expression.Expression.variableReference("bravo")))
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x", new LongestVariableNameFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}