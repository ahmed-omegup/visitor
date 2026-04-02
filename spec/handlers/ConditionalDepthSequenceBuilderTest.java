package spec.handlers;

import static lib.expression.Factory.*;

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
                conditional(literal("1"), conditional(literal("0"), literal("2"), literal("3")), literal("4"))
            )
        );
    }
}