package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.handlers.NodeCounter;

class NodeCounterTest {
    @Test
    void countsAllNodesInTraversalExpression() {
        assertEquals(42, new NodeCounter().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsSingleLiteralAsOneNode() {
        assertEquals(1, new NodeCounter().handle(lib.expression.ExpressionFactory.literal("3")));
    }
}