package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.visitors.LeafCounter;

abstract class LeafCounterTestBase<E> extends TestBase<E> {
    LeafCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsLeavesInTraversalExpression() {
        assertEquals(24,testSupport.sampleTraversalExpression().accept(testSupport.v.leafCounter()));
    }

    @Test
    void countsSingleLiteralAsOneLeaf() {
        assertEquals(1,factory.literal("3").accept(testSupport.v.leafCounter()));
    }
}

class LeafCounterTest extends LeafCounterTestBase<Expression> {
    LeafCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
