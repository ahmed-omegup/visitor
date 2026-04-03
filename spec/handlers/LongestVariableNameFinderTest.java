package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.LongestVariableNameFinder;
import port.IFactory;

class LongestVariableNameFinderTest {
    private final IFactory factory = new Factory();
    @Test
    void findsLongestVariableName() {
        assertEquals(
            "threshold",
factory.addition(factory.variableReference("threshold"), factory.literal("1")).accept(TestSupport.handlers().longestVariableNameFinder())
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",
factory.addition(factory.variableReference("alpha"), factory.variableReference("bravo")).accept(TestSupport.handlers().longestVariableNameFinder())
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x",TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().longestVariableNameFinder()));
    }
}