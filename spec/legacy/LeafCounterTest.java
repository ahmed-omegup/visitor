package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.legacy.HandlerFactory;
import lib.legacy.LeafCounter;

abstract class LeafCounterTestBase<E> extends TestBase<E> {
    LeafCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsLeavesInTraversalExpression() {
        assertEquals(24,testSupport.v.leafCounter().apply(testSupport.sampleTraversalExpression()));
    }

    @Test
    void countsSingleLiteralAsOneLeaf() {
        assertEquals(1,testSupport.v.leafCounter().apply(factory.literal("3")));
    }
}

class LeafCounterTest extends LeafCounterTestBase<Expression> {
    LeafCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
