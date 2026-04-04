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
import lib.visitors.HtmlExpressionExporter;

abstract class HtmlExpressionExporterTestBase<E extends Expression> extends TestBase<E> {
    HtmlExpressionExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsSimpleAdditionAsHtml() {
        assertEquals(
            "<span class=\"expression addition\"><span class=\"expression literal\">1</span> <span class=\"operator\">+</span> <span class=\"expression variable-reference\">x</span></span>",
factory.addition(factory.literal("1"), factory.variableReference("x")).accept(testSupport.v.htmlExpressionExporter())
        );
    }

    @Test
    void escapesHtmlSensitiveLeafValues() {
        assertEquals(
            "<span class=\"expression variable-reference\">a&amp;&lt;b&gt;&quot;</span>",
factory.variableReference("a&<b>\"").accept(testSupport.v.htmlExpressionExporter())
        );
    }

    @Test
    void exportsTraversalExpressionThroughAllVisitorBranches() {
        var html =testSupport.sampleTraversalExpression().accept(testSupport.v.htmlExpressionExporter());

        assertTrue(html.contains("class=\"expression conditional\""));
        assertTrue(html.contains("class=\"expression function-call\""));
        assertTrue(html.contains("class=\"operator\">&lt;="));
        assertTrue(html.contains("class=\"operator\">&gt;="));
        assertTrue(html.contains("class=\"operator\">&amp;&amp;"));
        assertTrue(html.contains("class=\"punctuation\">, </span>"));
    }
}

class HtmlExpressionExporterTest extends HtmlExpressionExporterTestBase<Expression> {
    HtmlExpressionExporterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
