package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.EvaluationOrderListBuilder;
import port.IFactory;

class EvaluationOrderListBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void recordsPreorderEvaluationSteps() {
        assertEquals(
            List.of("Addition", "VariableReference(x)", "Negation", "Literal(2)"),
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(TestSupport.handlers().evaluationOrderListBuilder())
        );
    }

    @Test
    void recordsTraversalExpressionEvaluationOrder() {
        var steps =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().evaluationOrderListBuilder());

        assertEquals("Conditional", steps.get(0));
        assertTrue(steps.contains("FunctionCall"));
        assertTrue(steps.contains("GreaterThanOrEqual"));
    }
}