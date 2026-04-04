package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.visitors.MaximumBranchingFactorFinder;

abstract class MaximumBranchingFactorFinderTestBase<E> extends TestBase<E> {
    MaximumBranchingFactorFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void returnsLargestObservedBranchingFactor() {
        assertEquals(8,testSupport.sampleTraversalExpression().accept(testSupport.v.maximumBranchingFactorFinder()));
        assertEquals(0,factory.literal("1").accept(testSupport.v.maximumBranchingFactorFinder()));
    }
}

class MaximumBranchingFactorFinderTest extends MaximumBranchingFactorFinderTestBase<Expression> {
    MaximumBranchingFactorFinderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
