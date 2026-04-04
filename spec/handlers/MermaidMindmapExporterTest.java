package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.MermaidMindmapExporter;

abstract class MermaidMindmapExporterTestBase<E extends Expression> extends TestBase<E> {
    MermaidMindmapExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsMindmapIndentation() {
        assertEquals(
            "mindmap\n"
                + "  Addition\n"
                + "    VariableReference(x)\n"
                + "    Literal(2)\n",
factory.addition(factory.variableReference("x"), factory.literal("2")).accept(testSupport.v.mermaidMindmapExporter())
        );
    }

    @Test
    void includesFunctionCallInTraversalMindmap() {
        assertTrue(testSupport.sampleTraversalExpression().accept(testSupport.v.mermaidMindmapExporter()).contains("    FunctionCall\n"));
    }
}

class MermaidMindmapExporterTest extends MermaidMindmapExporterTestBase<Expression> {
    MermaidMindmapExporterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
