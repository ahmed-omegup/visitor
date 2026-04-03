package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.VariableDepthSequenceBuilder;

class VariableDepthSequenceBuilderTest {
    @Test
    void recordsVariableDepthsInEncounterOrder() {
        assertEquals(List.of(3, 2),sampleTraversalExpression().accept(v.variableDepthSequenceBuilder()));
    }
}