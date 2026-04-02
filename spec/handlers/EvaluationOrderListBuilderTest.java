package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.EvaluationOrderListBuilder;

class EvaluationOrderListBuilderTest {
    @Test
    void recordsPreorderEvaluationSteps() {
        assertEquals(
            List.of("Addition", "VariableReference(x)", "Negation", "Literal(2)"),
            new EvaluationOrderListBuilder().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("2"))))
        );
    }

    @Test
    void recordsTraversalExpressionEvaluationOrder() {
        var steps = new EvaluationOrderListBuilder().handle(TestSupport.sampleTraversalExpression());

        assertEquals("Conditional", steps.get(0));
        assertTrue(steps.contains("FunctionCall"));
        assertTrue(steps.contains("GreaterThanOrEqual"));
    }
}