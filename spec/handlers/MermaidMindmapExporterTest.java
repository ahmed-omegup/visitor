package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.MermaidMindmapExporter;
import port.IFactory;

class MermaidMindmapExporterTest {
    private final IFactory factory = new Factory();
    @Test
    void exportsMindmapIndentation() {
        assertEquals(
            "mindmap\n"
                + "  Addition\n"
                + "    VariableReference(x)\n"
                + "    Literal(2)\n",
factory.addition(factory.variableReference("x"), factory.literal("2")).accept(v.mermaidMindmapExporter())
        );
    }

    @Test
    void includesFunctionCallInTraversalMindmap() {
        assertTrue(sampleTraversalExpression().accept(v.mermaidMindmapExporter()).contains("    FunctionCall\n"));
    }
}