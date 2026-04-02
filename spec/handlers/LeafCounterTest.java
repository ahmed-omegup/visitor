package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.handlers.LeafCounter;

class LeafCounterTest {
    private final Factory factory = new Factory();
    @Test
    void countsLeavesInTraversalExpression() {
        assertEquals(24, new LeafCounter().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsSingleLiteralAsOneLeaf() {
        assertEquals(1, new LeafCounter().handle(factory.literal("3")));
    }
}