package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.EvaluationComplexityScorer;

class EvaluationComplexityScorerTest {
    @Test
    void scoresTraversalExpressionFromExistingMetrics() {
        assertEquals(64,sampleTraversalExpression().accept(v.evaluationComplexityScorer()));
    }
}