package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.DeepestNodePathFinder;

abstract class DeepestNodePathFinderTestBase<E extends Expression> extends TestBase<E> {
    DeepestNodePathFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void findsDeepestPathInTraversalExpression() {
        assertEquals("0.2.7.0",testSupport.sampleTraversalExpression().accept(testSupport.v.deepestNodePathFinder()));
    }
}

class DeepestNodePathFinderTest extends DeepestNodePathFinderTestBase<Expression> {
    DeepestNodePathFinderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
