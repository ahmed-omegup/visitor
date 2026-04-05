package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.visitors.NodeCounter;

abstract class NodeCounterTestBase<E> extends TestBase<E> {
    NodeCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsAllNodesInTraversalExpression() {
        assertEquals(42,testSupport.v.nodeCounter().apply(testSupport.sampleTraversalExpression()));
    }

    @Test
    void countsSingleLiteralAsOneNode() {
        assertEquals(1,testSupport.v.nodeCounter().apply(factory.literal("3")));
    }
}

class NodeCounterTest extends NodeCounterTestBase<Expression> {
    NodeCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
