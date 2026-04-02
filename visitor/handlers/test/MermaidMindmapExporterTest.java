package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.MermaidMindmapExporter;

class MermaidMindmapExporterTest {
    @Test
    void exportsMindmapIndentation() {
        assertEquals(
            "mindmap\n"
                + "  Addition\n"
                + "    VariableReference(x)\n"
                + "    Literal(2)\n",
            new MermaidMindmapExporter().handle(new Addition(new VariableReference("x"), new Literal("2")))
        );
    }

    @Test
    void includesFunctionCallInTraversalMindmap() {
        assertTrue(new MermaidMindmapExporter().handle(TestSupport.sampleTraversalExpression()).contains("    FunctionCall\n"));
    }
}