package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.handlers.MermaidMindmapExporter;

abstract class MermaidMindmapExporterTestBase<E> extends TestBase<E> {
    MermaidMindmapExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsMindmapIndentation() {
        assertEquals(
            "mindmap\n"
                + "  Addition\n"
                + "    VariableReference(x)\n"
                + "    Literal(2)\n",testSupport.v.mermaidMindmapExporter().apply(factory.addition(factory.variableReference("x"), factory.literal("2")))
        );
    }

    @Test
    void includesFunctionCallInTraversalMindmap() {
        assertTrue(testSupport.v.mermaidMindmapExporter().apply(testSupport.sampleTraversalExpression()).contains("    FunctionCall\n"));
    }
}

class MermaidMindmapExporterTest extends MermaidMindmapExporterTestBase<Expression> {
    MermaidMindmapExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
