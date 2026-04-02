package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalCounter;

class ConditionalCounterTest {
    @Test
    void countsConditionalsAcrossTraversal() {
        assertEquals(1, new ConditionalCounter().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            2,
            new ConditionalCounter().handle(
                lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("0"), lib.expression.ExpressionFactory.literal("3"), lib.expression.ExpressionFactory.literal("4")))
            )
        );
    }
}