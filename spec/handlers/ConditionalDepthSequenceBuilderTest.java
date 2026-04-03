package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.visitors.ConditionalDepthSequenceBuilder;
import port.IFactory;

class ConditionalDepthSequenceBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void recordsConditionalDepthsInEncounterOrder() {
        assertEquals(List.of(0),sampleTraversalExpression().accept(v.conditionalDepthSequenceBuilder()));
        assertEquals(
            List.of(0, 1),
factory.conditional(factory.literal("1"), factory.conditional(factory.literal("0"), factory.literal("2"), factory.literal("3")), factory.literal("4")).accept(v.conditionalDepthSequenceBuilder())
        );
    }
}