package spec.handlers;

import static lib.expression.Factory.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.handlers.LeafCounter;

class LeafCounterTest {
    @Test
    void countsLeavesInTraversalExpression() {
        assertEquals(24, new LeafCounter().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsSingleLiteralAsOneLeaf() {
        assertEquals(1, new LeafCounter().handle(literal("3")));
    }
}