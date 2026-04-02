package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalDepthSequenceBuilder;

class ConditionalDepthSequenceBuilderTest {
    @Test
    void recordsConditionalDepthsInEncounterOrder() {
        assertEquals(List.of(0), new ConditionalDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            List.of(0, 1),
            new ConditionalDepthSequenceBuilder().handle(
                lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), lib.expression.Expression.conditional(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("2"), lib.expression.Expression.literal("3")), lib.expression.Expression.literal("4"))
            )
        );
    }
}