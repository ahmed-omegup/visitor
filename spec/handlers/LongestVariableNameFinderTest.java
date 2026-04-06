package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.handlers.LongestVariableNameFinder;

abstract class LongestVariableNameFinderTestBase<E> extends TestBase<E> {
    LongestVariableNameFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void findsLongestVariableName() {
        assertEquals(
            "threshold",testSupport.v.longestVariableNameFinder().apply(factory.addition(factory.variableReference("threshold"), factory.literal("1")))
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",testSupport.v.longestVariableNameFinder().apply(factory.addition(factory.variableReference("alpha"), factory.variableReference("bravo")))
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x",testSupport.v.longestVariableNameFinder().apply(testSupport.sampleTraversalExpression()));
    }
}

class LongestVariableNameFinderTest extends LongestVariableNameFinderTestBase<Expression> {
    LongestVariableNameFinderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
