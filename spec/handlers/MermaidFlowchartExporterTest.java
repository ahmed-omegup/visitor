package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.MermaidFlowchartExporter;
import port.IFactory;

class MermaidFlowchartExporterTest {
    private final IFactory factory = new Factory();
    @Test
    void exportsSimpleFlowchart() {
        assertEquals(
            "flowchart TD\n"
                + "  n0[\"Addition\"]\n"
                + "  n1[\"VariableReference(x)\"]\n"
                + "  n0 --> n1\n"
                + "  n2[\"Literal(2)\"]\n"
                + "  n0 --> n2\n",
factory.addition(factory.variableReference("x"), factory.literal("2")).accept(TestSupport.handlers().mermaidFlowchartExporter())
        );
    }

    @Test
    void exportsTraversalExpressionKinds() {
        var chart =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().mermaidFlowchartExporter());

        assertTrue(chart.contains("[\"Conditional\"]"));
        assertTrue(chart.contains("[\"FunctionCall\"]"));
        assertTrue(chart.contains("[\"GreaterThanOrEqual\"]"));
    }

    @Test
    void reusesExistingNodeForSharedExpressionReference() {
        var shared = factory.literal("a\"b");
        var chart =factory.addition(shared, shared).accept(TestSupport.handlers().mermaidFlowchartExporter());

        assertEquals(1, occurrences(chart, "[\"Literal(a\\\"b)\"]"));
        assertEquals(2, occurrences(chart, "n0 --> n1"));
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