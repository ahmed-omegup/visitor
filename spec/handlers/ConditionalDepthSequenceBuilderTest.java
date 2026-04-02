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
                lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("0"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.literal("3")), lib.expression.ExpressionFactory.literal("4"))
            )
        );
    }
}