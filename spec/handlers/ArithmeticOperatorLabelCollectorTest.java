package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.ArithmeticOperatorLabelCollector;

abstract class ArithmeticOperatorLabelCollectorTestBase<E> extends TestBase<E> {
    ArithmeticOperatorLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsArithmeticOperatorLabelsInPreorder() {
        assertEquals(
                of("Addition", "Subtraction", "Multiplication", "Division", "Modulo", "Exponentiation",
                        "Negation"),testSupport.v.arithmeticOperatorLabelCollector().apply(testSupport.sampleTraversalExpression()));
    }
}

class ArithmeticOperatorLabelCollectorTest extends ArithmeticOperatorLabelCollectorTestBase<Expression> {
    ArithmeticOperatorLabelCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
