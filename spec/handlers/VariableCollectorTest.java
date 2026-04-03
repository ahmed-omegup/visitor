package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.VariableCollector;

class VariableCollectorTest {
    @Test
    void collectsVariablesInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(List.of("x", "f")),
TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().variableCollector())
        );
    }
}