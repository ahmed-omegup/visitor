package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.LongestVariableNameFinder;

class LongestVariableNameFinderTest {
    @Test
    void findsLongestVariableName() {
        assertEquals(
            "threshold",
            new LongestVariableNameFinder().handle(new Addition(new VariableReference("threshold"), new Literal("1")))
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",
            new LongestVariableNameFinder().handle(new Addition(new VariableReference("alpha"), new VariableReference("bravo")))
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x", new LongestVariableNameFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}