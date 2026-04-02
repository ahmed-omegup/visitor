package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Literal;
import visitor.handlers.LeafCounter;

class LeafCounterTest {
    @Test
    void countsLeavesInTraversalExpression() {
        assertEquals(24, new LeafCounter().handle(TestSupport.sampleTraversalExpression()));
    }

    @Test
    void countsSingleLiteralAsOneLeaf() {
        assertEquals(1, new LeafCounter().handle(new Literal("3")));
    }
}