package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Literal;
import visitor.handlers.MaximumBranchingFactorFinder;

class MaximumBranchingFactorFinderTest {
    @Test
    void returnsLargestObservedBranchingFactor() {
        assertEquals(8, new MaximumBranchingFactorFinder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(0, new MaximumBranchingFactorFinder().handle(new Literal("1")));
    }
}