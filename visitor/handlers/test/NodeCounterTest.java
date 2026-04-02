package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Literal;
import visitor.handlers.NodeCounter;

class NodeCounterTest {
    @Test
    void countsAllNodesInTraversalExpression() {
        assertEquals(42, new NodeCounter().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsSingleLiteralAsOneNode() {
        assertEquals(1, new NodeCounter().handle(new Literal("3")));
    }
}