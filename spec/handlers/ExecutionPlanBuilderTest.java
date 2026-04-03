package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.visitors.ExecutionPlanBuilder;
import port.IFactory;

class ExecutionPlanBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void numbersIndentedExecutionSteps() {
        var expression = factory.addition(factory.literal("1"), factory.negation(factory.literal("2")));

        assertEquals(
            "1. inspect Addition\n"
                + "  2. inspect Literal(1)\n"
                + "  3. inspect Negation\n"
                + "    4. inspect Literal(2)\n",
expression.accept(v.executionPlanBuilder())
        );
    }

    @Test
    void visitsAllExpressionKindsInTraversalExpression() {
        var plan =sampleTraversalExpression().accept(v.executionPlanBuilder());

        assertTrue(plan.contains("inspect LessThanOrEqual"));
        assertTrue(plan.contains("inspect GreaterThanOrEqual"));
        assertTrue(plan.contains("inspect FunctionCall"));
    }
}