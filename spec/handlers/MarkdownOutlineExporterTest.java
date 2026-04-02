package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.MarkdownOutlineExporter;

class MarkdownOutlineExporterTest {
    @Test
    void exportsNestedExpressionsAsMarkdownOutline() {
        var expression = lib.expression.ExpressionFactory.addition(
            lib.expression.ExpressionFactory.functionCall(lib.expression.ExpressionFactory.variableReference("sum"), lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
            lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.variableReference("x"))
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