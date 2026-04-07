package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.legacy.EvaluationOrderListBuilder;
import lib.legacy.HandlerFactory;

abstract class EvaluationOrderListBuilderTestBase<E> extends TestBase<E> {
    EvaluationOrderListBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsPreorderEvaluationSteps() {
        assertEquals(
            of("Addition", "VariableReference(x)", "Negation", "Literal(2)"),testSupport.v.evaluationOrderListBuilder().apply(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void recordsTraversalExpressionEvaluationOrder() {
        var steps =testSupport.v.evaluationOrderListBuilder().apply(testSupport.sampleTraversalExpression());

        assertEquals("Conditional", steps.get(0));
        assertTrue(steps.contains("FunctionCall"));
        assertTrue(steps.contains("GreaterThanOrEqual"));
    }
}

class EvaluationOrderListBuilderTest extends EvaluationOrderListBuilderTestBase<Expression> {
    EvaluationOrderListBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
