package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.ShallowestLeafPathFinder;

class ShallowestLeafPathFinderTest {
    @Test
    void prefersTheEarliestLeafAtTheSmallestDepth() {
        assertEquals(
            "root.whenTrue",
            new ShallowestLeafPathFinder().handle(
                lib.expression.ExpressionFactory.conditional(
                    lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
                    lib.expression.ExpressionFactory.variableReference("x"),
                    lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("3"))
                )
            )
        );
    }

    @Test
    void findsTraversalExpressionShallowestLeaf() {
        assertEquals("root.whenFalse.callee", new ShallowestLeafPathFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}