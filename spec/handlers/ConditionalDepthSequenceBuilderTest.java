package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalDepthSequenceBuilder;

class ConditionalDepthSequenceBuilderTest {
    private final Factory factory = new Factory();
    @Test
    void recordsConditionalDepthsInEncounterOrder() {
        assertEquals(List.of(0), new ConditionalDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            List.of(0, 1),
            new ConditionalDepthSequenceBuilder().handle(
                factory.conditional(factory.literal("1"), factory.conditional(factory.literal("0"), factory.literal("2"), factory.literal("3")), factory.literal("4"))
            )
        );
    }
}