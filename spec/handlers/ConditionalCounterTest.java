package spec.handlers;

import static lib.expression.Factory.*;

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
                conditional(literal("1"), literal("2"), conditional(literal("0"), literal("3"), literal("4")))
            )
        );
    }
}