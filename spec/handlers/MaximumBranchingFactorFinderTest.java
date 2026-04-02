package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.handlers.MaximumBranchingFactorFinder;

class MaximumBranchingFactorFinderTest {
    @Test
    void returnsLargestObservedBranchingFactor() {
        assertEquals(8, new MaximumBranchingFactorFinder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(0, new MaximumBranchingFactorFinder().handle(lib.expression.ExpressionFactory.literal("1")));
    }
}