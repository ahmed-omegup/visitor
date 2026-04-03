package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.visitors.MaximumBranchingFactorFinder;
import port.IFactory;

class MaximumBranchingFactorFinderTest {
    private final IFactory factory = new Factory();
    @Test
    void returnsLargestObservedBranchingFactor() {
        assertEquals(8,sampleTraversalExpression().accept(v.maximumBranchingFactorFinder()));
        assertEquals(0,factory.literal("1").accept(v.maximumBranchingFactorFinder()));
    }
}