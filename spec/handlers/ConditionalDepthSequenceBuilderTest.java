package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalDepthSequenceBuilder;
import port.IFactory;

class ConditionalDepthSequenceBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void recordsConditionalDepthsInEncounterOrder() {
        assertEquals(List.of(0),TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().conditionalDepthSequenceBuilder()));
        assertEquals(
            List.of(0, 1),
factory.conditional(factory.literal("1"), factory.conditional(factory.literal("0"), factory.literal("2"), factory.literal("3")), factory.literal("4")).accept(TestSupport.handlers().conditionalDepthSequenceBuilder())
        );
    }
}