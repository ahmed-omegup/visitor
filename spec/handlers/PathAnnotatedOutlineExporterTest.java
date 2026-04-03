package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.PathAnnotatedOutlineExporter;
import port.IFactory;

class PathAnnotatedOutlineExporterTest {
    private final IFactory factory = new Factory();
    @Test
    void annotatesEachNodeWithItsTraversalPath() {
        assertEquals(
            "0 Addition\n"
                + "0.0 VariableReference(x)\n"
                + "0.1 Negation\n"
                + "0.1.0 Literal(2)\n",
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(TestSupport.handlers().pathAnnotatedOutlineExporter())
        );
    }

    @Test
    void coversConditionalAndFunctionCallPathBranches() {
        var outline =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().pathAnnotatedOutlineExporter());

        assertTrue(outline.contains("0 Conditional\n"));
        assertTrue(outline.contains("0.2 FunctionCall\n"));
        assertTrue(outline.contains("0.2.7 Negation\n"));
    }
}