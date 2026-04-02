package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.HtmlExpressionExporter;
import port.IFactory;

class HtmlExpressionExporterTest {
    private final IFactory factory = new Factory();
    @Test
    void exportsSimpleAdditionAsHtml() {
        assertEquals(
            "<span class=\"expression addition\"><span class=\"expression literal\">1</span> <span class=\"operator\">+</span> <span class=\"expression variable-reference\">x</span></span>",
            new HtmlExpressionExporter().handle(factory.addition(factory.literal("1"), factory.variableReference("x")))
        );
    }

    @Test
    void escapesHtmlSensitiveLeafValues() {
        assertEquals(
            "<span class=\"expression variable-reference\">a&amp;&lt;b&gt;&quot;</span>",
            new HtmlExpressionExporter().handle(factory.variableReference("a&<b>\""))
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