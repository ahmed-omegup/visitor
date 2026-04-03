package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.BinaryNodeDepthSequenceBuilder;

class BinaryNodeDepthSequenceBuilderTest {
    @Test
    void recordsBinaryNodeDepths() {
        assertEquals(
            List.of(1, 2, 3, 1, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2),
TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().binaryNodeDepthSequenceBuilder())
        );
    }
}