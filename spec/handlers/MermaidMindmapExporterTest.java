package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.MermaidMindmapExporter;
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
            new MermaidMindmapExporter().handle(factory.addition(factory.variableReference("x"), factory.literal("2")))
        );
    }

    @Test
    void includesFunctionCallInTraversalMindmap() {
        assertTrue(new MermaidMindmapExporter().handle(TestSupport.sampleTraversalExpression()).contains("    FunctionCall\n"));
    }
}