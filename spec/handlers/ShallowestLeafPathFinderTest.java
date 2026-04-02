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
                lib.expression.Expression.conditional(
                    lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")),
                    lib.expression.Expression.variableReference("x"),
                    lib.expression.Expression.negation(lib.expression.Expression.literal("3"))
                )
            )
        );
    }

    @Test
    void findsTraversalExpressionShallowestLeaf() {
        assertEquals("root.whenFalse.callee", new ShallowestLeafPathFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}