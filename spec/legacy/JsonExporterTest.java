package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.legacy.HandlerFactory;
import lib.legacy.JsonExporter;

abstract class JsonExporterTestBase<E> extends TestBase<E> {
    JsonExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsTraversalExpressionIncludingAllSemanticTypes() {
        var json =testSupport.v.jsonExporter().apply(testSupport.sampleTraversalExpression());

        for (var type : new String[] {
            "Conditional", "Conjunction", "LessThan", "VariableReference", "Literal",
            "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
            "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality",
            "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual", "Disjunction", "Negation"
        }) {
            assertTrue(json.contains("\"type\":\"" + type + "\""), "missing type " + type);
        }
    }

    @Test
    void escapesBackslashesAndQuotes() {
        var expression = factory.functionCall(
            factory.variableReference("say\"hi"), of(
            factory.literal("a\\b\"c")
        ));

        assertEquals(
            "{\"type\":\"FunctionCall\",\"children\":[{\"type\":\"VariableReference\",\"name\":\"say\\\"hi\"},{\"type\":\"Literal\",\"value\":\"a\\\\b\\\"c\"}]}",testSupport.v.jsonExporter().apply(expression)
        );
    }
}

class JsonExporterTest extends JsonExporterTestBase<Expression> {
    JsonExporterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
