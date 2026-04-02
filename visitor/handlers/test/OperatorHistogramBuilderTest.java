package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.handlers.OperatorHistogramBuilder;

class OperatorHistogramBuilderTest {
    @Test
    void countsRepeatedOperators() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("Addition", 2);
        expected.put("Negation", 1);

        assertEquals(
            expected,
            new OperatorHistogramBuilder().handle(
                new Addition(
                    new Addition(new Literal("1"), new Literal("2")),
                    new Negation(new Literal("3"))
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