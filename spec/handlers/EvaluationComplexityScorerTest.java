package spec.handlers;

import lib.expression.Expression;
import lib.handlers.EvaluationComplexityScorer;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class EvaluationComplexityScorerTestBase<E> extends TestBase<E> {
    EvaluationComplexityScorerTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void scoresTraversalExpressionFromExistingMetrics() {
        assertEquals(64,testSupport.v.evaluationComplexityScorer().apply(testSupport.sampleTraversalExpression()));
    }
}

class EvaluationComplexityScorerTest extends EvaluationComplexityScorerTestBase<Expression> {
    EvaluationComplexityScorerTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
