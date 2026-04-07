package spec.legacy;

import lib.expression.Expression;
import lib.legacy.BinaryOperatorLabelCollector;
import lib.legacy.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

abstract class BinaryOperatorLabelCollectorTestBase<E> extends TestBase<E> {
    BinaryOperatorLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsBinaryOperatorLabelsInPreorder() {
        assertEquals(
            of(
                "Conjunction", "LessThan", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
                "GreaterThanOrEqual", "Disjunction"
            ),testSupport.v.binaryOperatorLabelCollector().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class BinaryOperatorLabelCollectorTest extends BinaryOperatorLabelCollectorTestBase<Expression> {
    BinaryOperatorLabelCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
