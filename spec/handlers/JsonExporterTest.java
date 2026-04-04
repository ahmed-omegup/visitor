package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.FunctionCall;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.JsonExporter;
import port.IFactory;

abstract class JsonExporterTestBase<E extends Expression> extends TestBase<E> {
    JsonExporterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void exportsTraversalExpressionIncludingAllSemanticTypes() {
        var json =testSupport.sampleTraversalExpression().accept(testSupport.v.jsonExporter());

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
            factory.variableReference("say\"hi"),
            factory.literal("a\\b\"c")
        );

        assertEquals(
            "{\"type\":\"FunctionCall\",\"children\":[{\"type\":\"VariableReference\",\"name\":\"say\\\"hi\"},{\"type\":\"Literal\",\"value\":\"a\\\\b\\\"c\"}]}",
expression.accept(testSupport.v.jsonExporter())
        );
    }
}

class JsonExporterTest extends JsonExporterTestBase<Expression> {
    JsonExporterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
