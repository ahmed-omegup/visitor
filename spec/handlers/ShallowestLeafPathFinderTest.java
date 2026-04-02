package spec.handlers;

import static lib.expression.Factory.*;

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
                conditional(
                    addition(literal("1"), literal("2")),
                    variableReference("x"),
                    negation(literal("3"))
                )
            )
        );
    }

    @Test
    void findsTraversalExpressionShallowestLeaf() {
        assertEquals("root.whenFalse.callee", new ShallowestLeafPathFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}