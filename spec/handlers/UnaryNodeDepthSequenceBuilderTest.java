package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.UnaryNodeDepthSequenceBuilder;

class UnaryNodeDepthSequenceBuilderTest {
    @Test
    void recordsUnaryNodeDepths() {
        assertEquals(List.of(2, 2),sampleTraversalExpression().accept(v.unaryNodeDepthSequenceBuilder()));
    }
}