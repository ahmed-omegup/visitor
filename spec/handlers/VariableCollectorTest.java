package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.VariableCollector;

class VariableCollectorTest {
    @Test
    void collectsVariablesInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(List.of("x", "f")),
sampleTraversalExpression().accept(v.variableCollector())
        );
    }
}