package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalCounter;

class ConditionalCounterTest {
    private final Factory factory = new Factory();
    @Test
    void countsConditionalsAcrossTraversal() {
        assertEquals(1, new ConditionalCounter().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            2,
            new ConditionalCounter().handle(
                factory.conditional(factory.literal("1"), factory.literal("2"), factory.conditional(factory.literal("0"), factory.literal("3"), factory.literal("4")))
            )
        );
    }
}