package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.handlers.MaximumBranchingFactorFinder;
import port.IFactory;

class MaximumBranchingFactorFinderTest {
    private final IFactory factory = new Factory();
    @Test
    void returnsLargestObservedBranchingFactor() {
        assertEquals(8, new MaximumBranchingFactorFinder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(0, new MaximumBranchingFactorFinder().handle(factory.literal("1")));
    }
}