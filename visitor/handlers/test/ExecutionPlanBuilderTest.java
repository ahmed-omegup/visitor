package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.handlers.ExecutionPlanBuilder;

class ExecutionPlanBuilderTest {
    @Test
    void numbersIndentedExecutionSteps() {
        var expression = new Addition(new Literal("1"), new Negation(new Literal("2")));

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