package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.EvaluationComplexityScorer;

abstract class EvaluationComplexityScorerTestBase<E extends Expression> extends TestBase<E> {
    EvaluationComplexityScorerTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void scoresTraversalExpressionFromExistingMetrics() {
        assertEquals(64,testSupport.sampleTraversalExpression().accept(testSupport.v.evaluationComplexityScorer()));
    }
}

class EvaluationComplexityScorerTest extends EvaluationComplexityScorerTestBase<Expression> {
    EvaluationComplexityScorerTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
