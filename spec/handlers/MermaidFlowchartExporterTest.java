package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.MermaidFlowchartExporter;

abstract class MermaidFlowchartExporterTestBase<E> extends TestBase<E> {
    MermaidFlowchartExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsSimpleFlowchart() {
        assertEquals(
            "flowchart TD\n"
                + "  n0[\"Addition\"]\n"
                + "  n1[\"VariableReference(x)\"]\n"
                + "  n0 --> n1\n"
                + "  n2[\"Literal(2)\"]\n"
                + "  n0 --> n2\n",testSupport.v.mermaidFlowchartExporter().apply(factory.addition(factory.variableReference("x"), factory.literal("2")))
        );
    }

    @Test
    void exportsTraversalExpressionKinds() {
        var chart =testSupport.v.mermaidFlowchartExporter().apply(testSupport.sampleTraversalExpression());

        assertTrue(chart.contains("[\"Conditional\"]"));
        assertTrue(chart.contains("[\"FunctionCall\"]"));
        assertTrue(chart.contains("[\"GreaterThanOrEqual\"]"));
    }

    @Test
    void reusesExistingNodeForSharedExpressionReference() {
        var shared = factory.literal("a\"b");
        var chart =testSupport.v.mermaidFlowchartExporter().apply(factory.addition(shared, shared));

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

class MermaidFlowchartExporterTest extends MermaidFlowchartExporterTestBase<Expression> {
    MermaidFlowchartExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
