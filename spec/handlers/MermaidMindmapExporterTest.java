package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.MermaidMindmapExporter;

class MermaidMindmapExporterTest {
    @Test
    void exportsMindmapIndentation() {
        assertEquals(
            "mindmap\n"
                + "  Addition\n"
                + "    VariableReference(x)\n"
                + "    Literal(2)\n",
            new MermaidMindmapExporter().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.literal("2")))
        );
    }

    @Test
    void includesFunctionCallInTraversalMindmap() {
        assertTrue(new MermaidMindmapExporter().handle(TestSupport.sampleTraversalExpression()).contains("    FunctionCall\n"));
    }
}