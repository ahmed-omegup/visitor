package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Conditional;
import visitor.expression.Literal;
import visitor.handlers.ConditionalCounter;

class ConditionalCounterTest {
    @Test
    void countsConditionalsAcrossTraversal() {
        assertEquals(1, new ConditionalCounter().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            2,
            new ConditionalCounter().handle(
                new Conditional(new Literal("1"), new Literal("2"), new Conditional(new Literal("0"), new Literal("3"), new Literal("4")))
            )
        );
    }
}