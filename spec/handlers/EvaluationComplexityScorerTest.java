package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.EvaluationComplexityScorer;

class EvaluationComplexityScorerTest {
    @Test
    void scoresTraversalExpressionFromExistingMetrics() {
        assertEquals(64,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().evaluationComplexityScorer()));
    }
}