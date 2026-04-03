package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.handlers.NodeCounter;
import port.IFactory;

class NodeCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsAllNodesInTraversalExpression() {
        assertEquals(42,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().nodeCounter()));
    }

    @Test
    void countsSingleLiteralAsOneNode() {
        assertEquals(1,factory.literal("3").accept(TestSupport.handlers().nodeCounter()));
    }
}