package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.HtmlExpressionExporter;

class HtmlExpressionExporterTest {
    @Test
    void exportsSimpleAdditionAsHtml() {
        assertEquals(
            "<span class=\"expression addition\"><span class=\"expression literal\">1</span> <span class=\"operator\">+</span> <span class=\"expression variable-reference\">x</span></span>",
            new HtmlExpressionExporter().handle(new Addition(new Literal("1"), new VariableReference("x")))
        );
    }

    @Test
    void escapesHtmlSensitiveLeafValues() {
        assertEquals(
            "<span class=\"expression variable-reference\">a&amp;&lt;b&gt;&quot;</span>",
            new HtmlExpressionExporter().handle(new VariableReference("a&<b>\""))
        );
    }

    @Test
    void exportsTraversalExpressionThroughAllVisitorBranches() {
        var html = new HtmlExpressionExporter().handle(TestSupport.sampleTraversalExpression());

        assertTrue(html.contains("class=\"expression conditional\""));
        assertTrue(html.contains("class=\"expression function-call\""));
        assertTrue(html.contains("class=\"operator\">&lt;="));
        assertTrue(html.contains("class=\"operator\">&gt;="));
        assertTrue(html.contains("class=\"operator\">&amp;&amp;"));
        assertTrue(html.contains("class=\"punctuation\">, </span>"));
    }
}