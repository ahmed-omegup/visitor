package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.PathAnnotatedOutlineExporter;

class PathAnnotatedOutlineExporterTest {
    @Test
    void annotatesEachNodeWithItsTraversalPath() {
        assertEquals(
            "0 Addition\n"
                + "0.0 VariableReference(x)\n"
                + "0.1 Negation\n"
                + "0.1.0 Literal(2)\n",
            new PathAnnotatedOutlineExporter().handle(lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.variableReference("x"), lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("2"))))
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