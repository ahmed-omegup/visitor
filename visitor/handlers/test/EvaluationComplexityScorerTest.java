package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.handlers.EvaluationComplexityScorer;

class EvaluationComplexityScorerTest {
    @Test
    void scoresTraversalExpressionFromExistingMetrics() {
        assertEquals(64, new EvaluationComplexityScorer().handle(TestSupport.sampleTraversalExpression()));
    }
}