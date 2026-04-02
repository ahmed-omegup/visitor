package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.XmlExporter;

class XmlExporterTest {
    @Test
    void exportsNestedExpressionsAsIndentedXml() {
        var expression = lib.expression.Expression.addition(lib.expression.Expression.variableReference("x"), lib.expression.Expression.literal("2"));

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
        var xml = new XmlExporter().handle(lib.expression.Expression.addition(
            lib.expression.Expression.variableReference("a&b<q>\""),
            lib.expression.Expression.literal("<&\">")
        ));

        assertTrue(xml.contains("<VariableReference name=\"a&amp;b&lt;q&gt;&quot;\"/>"));
        assertTrue(xml.contains("<Literal value=\"&lt;&amp;&quot;&gt;\"/>"));
    }

    @Test
    void exportsTraversalExpressionThroughAllCompositeVisitMethods() {
        var xml = new XmlExporter().handle(TestSupport.sampleTraversalExpression());

        for (var tag : new String[] {
            "Conditional", "Conjunction", "LessThan", "LogicalNot", "Equality", "Addition",
            "Subtraction", "Multiplication", "Division", "Modulo", "FunctionCall",
            "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
            "GreaterThanOrEqual", "Disjunction", "Negation"
        }) {
            assertTrue(xml.contains("<" + tag + ">") || xml.contains("<" + tag + " "), "missing tag " + tag);
        }
        assertTrue(xml.contains("<VariableReference name=\"f\"/>"));
        assertTrue(xml.contains("<Literal value=\"4\"/>"));
    }

    @Test
    void exportsZeroArgumentFunctionCallWithoutArgumentLoopIterations() {
        assertEquals(
            "<FunctionCall>\n"
                + "  <VariableReference name=\"ping\"/>\n"
                + "</FunctionCall>\n",
            new XmlExporter().handle(lib.expression.Expression.functionCall(lib.expression.Expression.variableReference("ping")))
        );
    }
}