package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.ArithmeticOperatorLabelCollector;

class ArithmeticOperatorLabelCollectorTest {
    @Test
    void collectsArithmeticOperatorLabelsInPreorder() {
        assertEquals(
            List.of("Addition", "Subtraction", "Multiplication", "Division", "Modulo", "Exponentiation", "Negation"),
TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().arithmeticOperatorLabelCollector())
        );
    }
}