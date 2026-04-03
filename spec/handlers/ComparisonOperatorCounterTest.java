package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonOperatorCounter;

class ComparisonOperatorCounterTest {
    @Test
    void countsComparisonOperatorsInTraversalExpression() {
        assertEquals(6,sampleTraversalExpression().accept(v.comparisonOperatorCounter()));
    }
}