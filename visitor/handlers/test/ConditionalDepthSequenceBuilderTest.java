package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Conditional;
import visitor.expression.Literal;
import visitor.handlers.ConditionalDepthSequenceBuilder;

class ConditionalDepthSequenceBuilderTest {
    @Test
    void recordsConditionalDepthsInEncounterOrder() {
        assertEquals(List.of(0), new ConditionalDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
        assertEquals(
            List.of(0, 1),
            new ConditionalDepthSequenceBuilder().handle(
                new Conditional(new Literal("1"), new Conditional(new Literal("0"), new Literal("2"), new Literal("3")), new Literal("4"))
            )
        );
    }
}