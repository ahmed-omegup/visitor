package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.NodeTypeCollector;

class NodeTypeCollectorTest {
    @Test
    void collectsUniqueTypesInEncounterOrder() {
        assertEquals(
            new LinkedHashSet<>(List.of(
                "Conditional", "Conjunction", "LessThan", "VariableReference", "Literal",
                "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality",
                "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"
            )),
            new NodeTypeCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}