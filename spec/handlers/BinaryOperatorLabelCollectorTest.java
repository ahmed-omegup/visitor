package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.handlers.BinaryOperatorLabelCollector;

class BinaryOperatorLabelCollectorTest {
    @Test
    void collectsBinaryOperatorLabelsInPreorder() {
        assertEquals(
            List.of(
                "Conjunction", "LessThan", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
                "GreaterThanOrEqual", "Disjunction"
            ),
            new BinaryOperatorLabelCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}