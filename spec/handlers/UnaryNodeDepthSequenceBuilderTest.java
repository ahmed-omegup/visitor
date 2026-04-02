package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.UnaryNodeDepthSequenceBuilder;

class UnaryNodeDepthSequenceBuilderTest {
    @Test
    void recordsUnaryNodeDepths() {
        assertEquals(List.of(2, 2), new UnaryNodeDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}