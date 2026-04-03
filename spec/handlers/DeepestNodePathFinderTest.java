package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.DeepestNodePathFinder;

class DeepestNodePathFinderTest {
    @Test
    void findsDeepestPathInTraversalExpression() {
        assertEquals("0.2.7.0",sampleTraversalExpression().accept(v.deepestNodePathFinder()));
    }
}