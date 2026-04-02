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
                lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"), lib.expression.Expression.conditional(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("3"), lib.expression.Expression.literal("4")))
            )
        );
    }
}