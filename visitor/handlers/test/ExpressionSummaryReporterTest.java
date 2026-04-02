package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.handlers.ExpressionSummaryReporter;

class ExpressionSummaryReporterTest {
    @Test
    void reportsAggregatedMetrics() {
        assertEquals(
            "nodes=42, leaves=24, depth=5, variables=[x, f], literals=[10, 1, 0, 7, 2, 8, 2, 9, 4, 2, 3, 5, 6, 7, 1, 2, 2, 3, 3, 0, 1, 4]",
            new ExpressionSummaryReporter().handle(TestSupport.sampleTraversalExpression())
        );
    }
}