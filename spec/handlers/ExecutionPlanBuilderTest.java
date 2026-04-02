package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.handlers.ExecutionPlanBuilder;

class ExecutionPlanBuilderTest {
    @Test
    void numbersIndentedExecutionSteps() {
        var expression = lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("2")));

        assertEquals(
            "1. inspect Addition\n"
                + "  2. inspect Literal(1)\n"
                + "  3. inspect Negation\n"
                + "    4. inspect Literal(2)\n",
            new ExecutionPlanBuilder().handle(expression)
        );
    }

    @Test
    void visitsAllExpressionKindsInTraversalExpression() {
        var plan = new ExecutionPlanBuilder().handle(TestSupport.sampleTraversalExpression());

        assertTrue(plan.contains("inspect LessThanOrEqual"));
        assertTrue(plan.contains("inspect GreaterThanOrEqual"));
        assertTrue(plan.contains("inspect FunctionCall"));
    }
}