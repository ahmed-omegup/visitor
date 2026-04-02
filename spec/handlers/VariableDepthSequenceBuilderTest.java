package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.VariableDepthSequenceBuilder;

class VariableDepthSequenceBuilderTest {
    @Test
    void recordsVariableDepthsInEncounterOrder() {
        assertEquals(List.of(3, 2), new VariableDepthSequenceBuilder().handle(TestSupport.sampleTraversalExpression()));
    }
}