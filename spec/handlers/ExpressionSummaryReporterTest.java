package spec.handlers;

import lib.expression.Expression;
import lib.handlers.ExpressionSummaryReporter;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class ExpressionSummaryReporterTestBase<E> extends TestBase<E> {
    ExpressionSummaryReporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void reportsAggregatedMetrics() {
        assertEquals(
            "nodes=42, leaves=24, depth=5, variables=[x, f], literals=[10, 1, 0, 7, 2, 8, 2, 9, 4, 2, 3, 5, 6, 7, 1, 2, 2, 3, 3, 0, 1, 4]",testSupport.v.expressionSummaryReporter().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class ExpressionSummaryReporterTest extends ExpressionSummaryReporterTestBase<Expression> {
    ExpressionSummaryReporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
