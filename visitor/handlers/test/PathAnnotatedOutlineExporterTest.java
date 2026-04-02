package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.PathAnnotatedOutlineExporter;

class PathAnnotatedOutlineExporterTest {
    @Test
    void annotatesEachNodeWithItsTraversalPath() {
        assertEquals(
            "0 Addition\n"
                + "0.0 VariableReference(x)\n"
                + "0.1 Negation\n"
                + "0.1.0 Literal(2)\n",
            new PathAnnotatedOutlineExporter().handle(new Addition(new VariableReference("x"), new Negation(new Literal("2"))))
        );
    }

    @Test
    void coversConditionalAndFunctionCallPathBranches() {
        var outline = new PathAnnotatedOutlineExporter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(outline.contains("0 Conditional\n"));
        assertTrue(outline.contains("0.2 FunctionCall\n"));
        assertTrue(outline.contains("0.2.7 Negation\n"));
    }
}