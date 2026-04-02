package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.handlers.OperatorHistogramBuilder;

class OperatorHistogramBuilderTest {
    @Test
    void countsRepeatedOperators() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("Addition", 2);
        expected.put("Negation", 1);

        assertEquals(
            expected,
            new OperatorHistogramBuilder().handle(
                lib.expression.ExpressionFactory.addition(
                    lib.expression.ExpressionFactory.addition(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2")),
                    lib.expression.ExpressionFactory.negation(lib.expression.ExpressionFactory.literal("3"))
                )
            )
        );
    }

    @Test
    void countsAllOperatorKindsInTraversalExpression() {
        var histogram = new OperatorHistogramBuilder().handle(TestSupport.sampleTraversalExpression());

        for (var operator : new String[] {
            "Conditional", "Conjunction", "LessThan", "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
            "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
            "GreaterThanOrEqual", "Disjunction", "Negation"
        }) {
            assertEquals(1, histogram.get(operator));
        }
    }
}