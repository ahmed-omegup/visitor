package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.visitors.OperatorHistogramBuilder;
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
factory.addition(
                    factory.addition(factory.literal("1"), factory.literal("2")),
                    factory.negation(factory.literal("3"))
                ).accept(v.operatorHistogramBuilder())
        );
    }

    @Test
    void countsAllOperatorKindsInTraversalExpression() {
        var histogram =sampleTraversalExpression().accept(v.operatorHistogramBuilder());

        for (var operator : new String[] {
            "Conditional", "Conjunction", "LessThan", "LogicalNot", "Equality", "Addition", "Subtraction", "Multiplication",
            "Division", "Modulo", "FunctionCall", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
            "GreaterThanOrEqual", "Disjunction", "Negation"
        }) {
            assertEquals(1, histogram.get(operator));
        }
    }
}