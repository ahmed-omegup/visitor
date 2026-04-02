package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.ShallowestLeafPathFinder;

class ShallowestLeafPathFinderTest {
    @Test
    void prefersTheEarliestLeafAtTheSmallestDepth() {
        assertEquals(
            "root.whenTrue",
            new ShallowestLeafPathFinder().handle(
                new Conditional(
                    new Addition(new Literal("1"), new Literal("2")),
                    new VariableReference("x"),
                    new Negation(new Literal("3"))
                )
            )
        );
    }

    @Test
    void findsTraversalExpressionShallowestLeaf() {
        assertEquals("root.whenFalse.callee", new ShallowestLeafPathFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}