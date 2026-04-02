package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.handlers.ComparisonOperatorCounter;

class ComparisonOperatorCounterTest {
    @Test
    void countsComparisonOperatorsInTraversalExpression() {
        assertEquals(6, new ComparisonOperatorCounter().handle(TestSupport.sampleTraversalExpression()));
    }
}