package spec.handlers;

import lib.expression.Expression;
import lib.handlers.DeepestNodePathFinder;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class DeepestNodePathFinderTestBase<E> extends TestBase<E> {
    DeepestNodePathFinderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void findsDeepestPathInTraversalExpression() {
        assertEquals("0.2.7.0",testSupport.v.deepestNodePathFinder().apply(testSupport.sampleTraversalExpression()));
    }
}

class DeepestNodePathFinderTest extends DeepestNodePathFinderTestBase<Expression> {
    DeepestNodePathFinderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
