package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.handlers.DotGraphExporter;
import port.IFactory;

class DotGraphExporterTest {
    private final IFactory factory = new Factory();
    @Test
    void exportsTraversalExpressionAsGraphvizGraph() {
        var graph =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().dotGraphExporter());

        assertTrue(graph.startsWith("digraph Expression {\n"));
        for (var label : new String[] {
            "Conditional", "Conjunction", "LessThan", "VariableReference(x)", "Literal(10)",
            "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
            "Division", "Modulo", "FunctionCall", "VariableReference(f)", "Exponentiation",
            "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"
        }) {
            assertTrue(graph.contains("label=\"" + label + "\""), "missing label " + label);
        }
    }

    @Test
    void reusesExistingNodeIdsForSharedSubexpressions() {
        var shared = factory.literal("a\"b");
        var graph =factory.addition(shared, shared).accept(TestSupport.handlers().dotGraphExporter());

        assertEquals(1, occurrences(graph, "n1 [label=\"Literal(a\\\"b)\"]"));
        assertEquals(2, occurrences(graph, "n0 -> n1;"));
    }

    private int occurrences(String text, String needle) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(needle, index)) >= 0) {
            count++;
            index += needle.length();
        }
        return count;
    }
}