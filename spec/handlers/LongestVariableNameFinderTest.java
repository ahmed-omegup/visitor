package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.LongestVariableNameFinder;
import port.IFactory;

class LongestVariableNameFinderTest {
    private final IFactory factory = new Factory();
    @Test
    void findsLongestVariableName() {
        assertEquals(
            "threshold",
factory.addition(factory.variableReference("threshold"), factory.literal("1")).accept(v.longestVariableNameFinder())
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",
factory.addition(factory.variableReference("alpha"), factory.variableReference("bravo")).accept(v.longestVariableNameFinder())
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x",sampleTraversalExpression().accept(v.longestVariableNameFinder()));
    }
}