package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryOperatorLabelCollector;

class BinaryOperatorLabelCollectorTest {
    @Test
    void collectsBinaryOperatorLabelsInPreorder() {
        assertEquals(
            List.of(
                "Conjunction", "LessThan", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
                "GreaterThanOrEqual", "Disjunction"
            ),
sampleTraversalExpression().accept(v.binaryOperatorLabelCollector())
        );
    }
}