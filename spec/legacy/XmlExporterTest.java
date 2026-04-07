package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.XmlExporter;

abstract class XmlExporterTestBase<E> extends TestBase<E> {
    XmlExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsNestedExpressionsAsIndentedXml() {
        var expression = factory.addition(factory.variableReference("x"), factory.literal("2"));

        assertEquals(
            "<Addition>\n"
                + "  <VariableReference name=\"x\"/>\n"
                + "  <Literal value=\"2\"/>\n"
                + "</Addition>\n",testSupport.v.xmlExporter().apply(expression)
        );
    }

    @Test
    void escapesSpecialCharacters() {
        var xml =testSupport.v.xmlExporter().apply(factory.addition(
            factory.variableReference("a&b<q>\""),
            factory.literal("<&\">")
        ));

        assertTrue(xml.contains("<VariableReference name=\"a&amp;b&lt;q&gt;&quot;\"/>"));
        assertTrue(xml.contains("<Literal value=\"&lt;&amp;&quot;&gt;\"/>"));
    }

    @Test
    void exportsTraversalExpressionThroughAllCompositeVisitMethods() {
        var xml =testSupport.v.xmlExporter().apply(testSupport.sampleTraversalExpression());

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
                + "</FunctionCall>\n",testSupport.v.xmlExporter().apply(factory.functionCall(factory.variableReference("ping"), of()))
        );
    }
}

class XmlExporterTest extends XmlExporterTestBase<Expression> {
    XmlExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
