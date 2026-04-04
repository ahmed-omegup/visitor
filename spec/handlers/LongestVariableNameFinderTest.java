package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.LongestVariableNameFinder;
import port.IFactory;

abstract class LongestVariableNameFinderTestBase<E extends Expression> extends TestBase<E> {
    LongestVariableNameFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void findsLongestVariableName() {
        assertEquals(
            "threshold",
factory.addition(factory.variableReference("threshold"), factory.literal("1")).accept(testSupport.v.longestVariableNameFinder())
        );
    }

    @Test
    void keepsLeftVariableOnEqualLengthTie() {
        assertEquals(
            "alpha",
factory.addition(factory.variableReference("alpha"), factory.variableReference("bravo")).accept(testSupport.v.longestVariableNameFinder())
        );
    }

    @Test
    void findsLongestVariableAcrossTraversalExpression() {
        assertEquals("x",testSupport.sampleTraversalExpression().accept(testSupport.v.longestVariableNameFinder()));
    }
}

class LongestVariableNameFinderTest extends LongestVariableNameFinderTestBase<Expression> {
    LongestVariableNameFinderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
