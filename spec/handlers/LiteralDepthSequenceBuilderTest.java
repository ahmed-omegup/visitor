package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.LiteralDepthSequenceBuilder;

class LiteralDepthSequenceBuilderTest {
    @Test
    void recordsLiteralDepthsInEncounterOrder() {
        assertEquals(
            List.of(3, 4, 4, 3, 3, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3),
sampleTraversalExpression().accept(v.literalDepthSequenceBuilder())
        );
    }
}