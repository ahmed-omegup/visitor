package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;
import visitor.handlers.XmlExporter;

class XmlExporterTest {
    @Test
    void exportsNestedExpressionsAsIndentedXml() {
        var expression = new Addition(new VariableReference("x"), new Literal("2"));

        assertEquals(
            "<Addition>\n"
                + "  <VariableReference name=\"x\"/>\n"
                + "  <Literal value=\"2\"/>\n"
                + "</Addition>\n",
            new XmlExporter().handle(expression)
        );
    }

    @Test
    void escapesSpecialCharacters() {
        var xml = new XmlExporter().handle(new Addition(
            new VariableReference("a&b<q>\""),
            new Literal("<&\">")
        ));

        assertTrue(xml.contains("<VariableReference name=\"a&amp;b&lt;q&gt;&quot;\"/>"));
        assertTrue(xml.contains("<Literal value=\"&lt;&amp;&quot;&gt;\"/>"));
    }
}