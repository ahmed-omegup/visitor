package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.LiteralDepthSequenceBuilder;

class LiteralDepthSequenceBuilderTest {
    @Test
    void recordsLiteralDepthsInEncounterOrder() {
        assertEquals(
            List.of(3, 4, 4, 3, 3, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3),
TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().literalDepthSequenceBuilder())
        );
    }
}