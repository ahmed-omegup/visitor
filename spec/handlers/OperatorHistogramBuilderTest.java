package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.handlers.OperatorHistogramBuilder;
import port.IFactory;

class OperatorHistogramBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void countsRepeatedOperators() {
        var expected = new LinkedHashMap<String, Integer>();
        expected.put("Addition", 2);
        expected.put("Negation", 1);

        assertEquals(
            expected,
            new OperatorHistogramBuilder().handle(
                factory.addition(
                    factory.addition(factory.literal("1"), factory.literal("2")),
                    factory.negation(factory.literal("3"))
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