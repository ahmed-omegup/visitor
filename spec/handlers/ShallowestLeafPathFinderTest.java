package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.ShallowestLeafPathFinder;
import port.IFactory;

class ShallowestLeafPathFinderTest {
    private final IFactory factory = new Factory();
    @Test
    void prefersTheEarliestLeafAtTheSmallestDepth() {
        assertEquals(
            "root.whenTrue",
factory.conditional(
                    factory.addition(factory.literal("1"), factory.literal("2")),
                    factory.variableReference("x"),
                    factory.negation(factory.literal("3"))
                ).accept(TestSupport.handlers().shallowestLeafPathFinder())
        );
    }

    @Test
    void findsTraversalExpressionShallowestLeaf() {
        assertEquals("root.whenFalse.callee",TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().shallowestLeafPathFinder()));
    }
}