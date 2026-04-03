package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.visitors.NodeCounter;
import port.IFactory;

class NodeCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsAllNodesInTraversalExpression() {
        assertEquals(42,sampleTraversalExpression().accept(v.nodeCounter()));
    }

    @Test
    void countsSingleLiteralAsOneNode() {
        assertEquals(1,factory.literal("3").accept(v.nodeCounter()));
    }
}