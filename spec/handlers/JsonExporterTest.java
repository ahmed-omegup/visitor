package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.JsonExporter;

class JsonExporterTest {
    @Test
    void exportsTraversalExpressionIncludingAllSemanticTypes() {
        var json = new JsonExporter().handle(TestSupport.sampleTraversalExpression());

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
        var expression = lib.expression.Expression.functionCall(
            lib.expression.Expression.variableReference("say\"hi"),
            lib.expression.Expression.literal("a\\b\"c")
        );

        assertEquals(
            "{\"type\":\"FunctionCall\",\"children\":[{\"type\":\"VariableReference\",\"name\":\"say\\\"hi\"},{\"type\":\"Literal\",\"value\":\"a\\\\b\\\"c\"}]}",
            new JsonExporter().handle(expression)
        );
    }
}