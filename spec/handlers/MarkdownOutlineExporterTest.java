package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;
import lib.handlers.MarkdownOutlineExporter;

abstract class MarkdownOutlineExporterTestBase<E> extends TestBase<E> {
    MarkdownOutlineExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsNestedExpressionsAsMarkdownOutline() {
        var expression = factory.addition(
            factory.functionCall(factory.variableReference("sum"), of( factory.literal("1"), factory.literal("2"))),
            factory.negation(factory.variableReference("x"))
        );

        assertEquals(
            "- Addition\n"
                + "  - FunctionCall\n"
                + "    - VariableReference(sum)\n"
                + "    - Literal(1)\n"
                + "    - Literal(2)\n"
                + "  - Negation\n"
                + "    - VariableReference(x)\n",testSupport.v.markdownOutlineExporter().apply(expression)
        );
    }

    @Test
    void visitsAllExpressionTypes() {
        var markdown =testSupport.v.markdownOutlineExporter().apply(testSupport.sampleTraversalExpression());

        assertTrue(markdown.contains("- LessThanOrEqual\n"));
        assertTrue(markdown.contains("- GreaterThanOrEqual\n"));
        assertTrue(markdown.contains("- Disjunction\n"));
    }
}

class MarkdownOutlineExporterTest extends MarkdownOutlineExporterTestBase<Expression> {
    MarkdownOutlineExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
