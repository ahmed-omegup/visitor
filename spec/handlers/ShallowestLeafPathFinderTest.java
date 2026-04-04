package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.ShallowestLeafPathFinder;

abstract class ShallowestLeafPathFinderTestBase<E extends Expression> extends TestBase<E> {
    ShallowestLeafPathFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void prefersTheEarliestLeafAtTheSmallestDepth() {
        assertEquals(
            "root.whenTrue",
factory.conditional(
                    factory.addition(factory.literal("1"), factory.literal("2")),
                    factory.variableReference("x"),
                    factory.negation(factory.literal("3"))
                ).accept(testSupport.v.shallowestLeafPathFinder())
        );
    }

    @Test
    void findsTraversalExpressionShallowestLeaf() {
        assertEquals("root.whenFalse.callee",testSupport.sampleTraversalExpression().accept(testSupport.v.shallowestLeafPathFinder()));
    }
}

class ShallowestLeafPathFinderTest extends ShallowestLeafPathFinderTestBase<Expression> {
    ShallowestLeafPathFinderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
