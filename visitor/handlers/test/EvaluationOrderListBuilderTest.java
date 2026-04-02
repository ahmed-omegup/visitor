package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.EvaluationOrderListBuilder;

class EvaluationOrderListBuilderTest {
    @Test
    void recordsPreorderEvaluationSteps() {
        assertEquals(
            List.of("Addition", "VariableReference(x)", "Negation", "Literal(2)"),
            new EvaluationOrderListBuilder().handle(new Addition(new VariableReference("x"), new Negation(new Literal("2"))))
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