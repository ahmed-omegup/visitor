package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.visitors.ExecutionPlanBuilder;

abstract class ExecutionPlanBuilderTestBase<E extends Expression> extends TestBase<E> {
    ExecutionPlanBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void numbersIndentedExecutionSteps() {
        var expression = factory.addition(factory.literal("1"), factory.negation(factory.literal("2")));

        assertEquals(
            "1. inspect Addition\n"
                + "  2. inspect Literal(1)\n"
                + "  3. inspect Negation\n"
                + "    4. inspect Literal(2)\n",
expression.accept(testSupport.v.executionPlanBuilder())
        );
    }

    @Test
    void visitsAllExpressionKindsInTraversalExpression() {
        var plan =testSupport.sampleTraversalExpression().accept(testSupport.v.executionPlanBuilder());

        assertTrue(plan.contains("inspect LessThanOrEqual"));
        assertTrue(plan.contains("inspect GreaterThanOrEqual"));
        assertTrue(plan.contains("inspect FunctionCall"));
    }
}

class ExecutionPlanBuilderTest extends ExecutionPlanBuilderTestBase<Expression> {
    ExecutionPlanBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
