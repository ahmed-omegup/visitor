package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.handlers.ArithmeticOperatorLabelCollector;

class ArithmeticOperatorLabelCollectorTest {
    @Test
    void collectsArithmeticOperatorLabelsInPreorder() {
        assertEquals(
            List.of("Addition", "Subtraction", "Multiplication", "Division", "Modulo", "Exponentiation", "Negation"),
            new ArithmeticOperatorLabelCollector().handle(TestSupport.sampleTraversalExpression())
        );
    }
}