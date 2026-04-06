package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.handlers.HandlerFactory;
import lib.handlers.MaximumBranchingFactorFinder;

abstract class MaximumBranchingFactorFinderTestBase<E> extends TestBase<E> {
    MaximumBranchingFactorFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void returnsLargestObservedBranchingFactor() {
        assertEquals(8,testSupport.v.maximumBranchingFactorFinder().apply(testSupport.sampleTraversalExpression()));
        assertEquals(0,testSupport.v.maximumBranchingFactorFinder().apply(factory.literal("1")));
    }
}

class MaximumBranchingFactorFinderTest extends MaximumBranchingFactorFinderTestBase<Expression> {
    MaximumBranchingFactorFinderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
