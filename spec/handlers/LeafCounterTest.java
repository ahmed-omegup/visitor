package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.visitors.LeafCounter;
import port.IFactory;

class LeafCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsLeavesInTraversalExpression() {
        assertEquals(24,sampleTraversalExpression().accept(v.leafCounter()));
    }

    @Test
    void countsSingleLiteralAsOneLeaf() {
        assertEquals(1,factory.literal("3").accept(v.leafCounter()));
    }
}