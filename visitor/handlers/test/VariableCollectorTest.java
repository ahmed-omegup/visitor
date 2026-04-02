package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.handlers.VariableCollector;

class VariableCollectorTest {
    @Test
    void collectsVariablesInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(List.of("x", "f")),
            new VariableCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}