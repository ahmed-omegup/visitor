package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.handlers.DeepestNodePathFinder;

class DeepestNodePathFinderTest {
    @Test
    void findsDeepestPathInTraversalExpression() {
        assertEquals("0.2.7.0", new DeepestNodePathFinder().handle(TestSupport.sampleTraversalExpression()));
    }
}