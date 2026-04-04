package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.MarkdownOutlineExporter;
import port.IFactory;

abstract class MarkdownOutlineExporterTestBase<E extends Expression> extends TestBase<E> {
    MarkdownOutlineExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsNestedExpressionsAsMarkdownOutline() {
        var expression = factory.addition(
            factory.functionCall(factory.variableReference("sum"), factory.literal("1"), factory.literal("2")),
            factory.negation(factory.variableReference("x"))
        );

        assertEquals(
            "- Addition\n"
                + "  - FunctionCall\n"
                + "    - VariableReference(sum)\n"
                + "    - Literal(1)\n"
                + "    - Literal(2)\n"
                + "  - Negation\n"
                + "    - VariableReference(x)\n",
expression.accept(testSupport.v.markdownOutlineExporter())
        );
    }

    @Test
    void visitsAllExpressionTypes() {
        var markdown =testSupport.sampleTraversalExpression().accept(testSupport.v.markdownOutlineExporter());

        assertTrue(markdown.contains("- LessThanOrEqual\n"));
        assertTrue(markdown.contains("- GreaterThanOrEqual\n"));
        assertTrue(markdown.contains("- Disjunction\n"));
    }
}

class MarkdownOutlineExporterTest extends MarkdownOutlineExporterTestBase<Expression> {
    MarkdownOutlineExporterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
