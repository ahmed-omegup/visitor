package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.ArithmeticOperatorLabelCollector;
import static spec.handlers.TestSupport.*;

class ArithmeticOperatorLabelCollectorTest {
    @Test
    void collectsArithmeticOperatorLabelsInPreorder() {
        assertEquals(
                List.of("Addition", "Subtraction", "Multiplication", "Division", "Modulo", "Exponentiation",
                        "Negation"),
                sampleTraversalExpression()
                        .accept(v.arithmeticOperatorLabelCollector()));
    }
}