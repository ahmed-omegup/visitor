package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.FunctionCall;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.MarkdownOutlineExporter;

class MarkdownOutlineExporterTest {
    @Test
    void exportsNestedExpressionsAsMarkdownOutline() {
        var expression = new Addition(
            new FunctionCall(new VariableReference("sum"), new Literal("1"), new Literal("2")),
            new Negation(new VariableReference("x"))
        );

        assertEquals(
            "- Addition\n"
                + "  - FunctionCall\n"
                + "    - VariableReference(sum)\n"
                + "    - Literal(1)\n"
                + "    - Literal(2)\n"
                + "  - Negation\n"
                + "    - VariableReference(x)\n",
            new MarkdownOutlineExporter().handle(expression)
        );
    }

    @Test
    void visitsAllExpressionTypes() {
        var markdown = new MarkdownOutlineExporter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(markdown.contains("- LessThanOrEqual\n"));
        assertTrue(markdown.contains("- GreaterThanOrEqual\n"));
        assertTrue(markdown.contains("- Disjunction\n"));
    }
}