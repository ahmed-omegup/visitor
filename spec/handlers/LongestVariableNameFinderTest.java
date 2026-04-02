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
            new LongestVariableNameFinder().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("threshold"), lib.expression.ExpressionFactory.literal("1")))
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestVariableNameFinder().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("alpha"), lib.expression.ExpressionFactory.variableReference("bravo")))
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x", new LongestVariableNameFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}