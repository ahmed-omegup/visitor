package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Literal;
import lib.visitors.NodeCounter;
import port.IFactory;

abstract class NodeCounterTestBase<E extends Expression> extends TestBase<E> {
    NodeCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsAllNodesInTraversalExpression() {
        assertEquals(42,testSupport.sampleTraversalExpression().accept(testSupport.v.nodeCounter()));
    }

    @Test
    void countsSingleLiteralAsOneNode() {
        assertEquals(1,factory.literal("3").accept(testSupport.v.nodeCounter()));
    }
}

class NodeCounterTest extends NodeCounterTestBase<Expression> {
    NodeCounterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
