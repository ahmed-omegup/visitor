package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.DeepestNodePathFinder;

class DeepestNodePathFinderTest {
    @Test
    void findsDeepestPathInTraversalExpression() {
        assertEquals("0.2.7.0",TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().deepestNodePathFinder()));
    }
}