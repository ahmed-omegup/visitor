package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.ArithmeticOperatorLabelCollector;

abstract class ArithmeticOperatorLabelCollectorTestBase<E extends Expression> extends TestBase<E> {
    ArithmeticOperatorLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsArithmeticOperatorLabelsInPreorder() {
        assertEquals(
                List.of("Addition", "Subtraction", "Multiplication", "Division", "Modulo", "Exponentiation",
                        "Negation"),
                testSupport.sampleTraversalExpression()
                        .accept(testSupport.v.arithmeticOperatorLabelCollector()));
    }
}

class ArithmeticOperatorLabelCollectorTest extends ArithmeticOperatorLabelCollectorTestBase<Expression> {
    ArithmeticOperatorLabelCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
