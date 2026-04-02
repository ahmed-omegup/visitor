package spec.handlers;

import static lib.expression.Factory.*;

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
        var expression = addition(
            functionCall(variableReference("sum"), literal("1"), literal("2")),
            negation(variableReference("x"))
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