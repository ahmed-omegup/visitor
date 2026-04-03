package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonOperatorCounter;

class ComparisonOperatorCounterTest {
    @Test
    void countsComparisonOperatorsInTraversalExpression() {
        assertEquals(6,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().comparisonOperatorCounter()));
    }
}