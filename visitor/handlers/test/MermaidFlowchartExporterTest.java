package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.MermaidFlowchartExporter;

class MermaidFlowchartExporterTest {
    @Test
    void exportsSimpleFlowchart() {
        assertEquals(
            "flowchart TD\n"
                + "  n0[\"Addition\"]\n"
                + "  n1[\"VariableReference(x)\"]\n"
                + "  n0 --> n1\n"
                + "  n2[\"Literal(2)\"]\n"
                + "  n0 --> n2\n",
            new MermaidFlowchartExporter().handle(new Addition(new VariableReference("x"), new Literal("2")))
        );
    }

    @Test
    void exportsTraversalExpressionKinds() {
        var chart = new MermaidFlowchartExporter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(chart.contains("[\"Conditional\"]"));
        assertTrue(chart.contains("[\"FunctionCall\"]"));
        assertTrue(chart.contains("[\"GreaterThanOrEqual\"]"));
    }

    @Test
    void reusesExistingNodeForSharedExpressionReference() {
        var shared = new Literal("a\"b");
        var chart = new MermaidFlowchartExporter().handle(new Addition(shared, shared));

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