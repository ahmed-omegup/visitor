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
        assertEquals(8,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().maximumBranchingFactorFinder()));
        assertEquals(0,factory.literal("1").accept(TestSupport.handlers().maximumBranchingFactorFinder()));
    }
}