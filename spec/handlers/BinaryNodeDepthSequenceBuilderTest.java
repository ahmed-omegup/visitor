package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryNodeDepthSequenceBuilder;

class BinaryNodeDepthSequenceBuilderTest {
    @Test
    void recordsBinaryNodeDepths() {
        assertEquals(
            List.of(1, 2, 3, 1, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2),
sampleTraversalExpression().accept(v.binaryNodeDepthSequenceBuilder())
        );
    }
}