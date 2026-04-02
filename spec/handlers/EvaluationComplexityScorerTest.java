package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.handlers.EvaluationComplexityScorer;

class EvaluationComplexityScorerTest {
    @Test
    void scoresTraversalExpressionFromExistingMetrics() {
        assertEquals(64, new EvaluationComplexityScorer().handle(TestSupport.sampleTraversalExpression()));
    }
}