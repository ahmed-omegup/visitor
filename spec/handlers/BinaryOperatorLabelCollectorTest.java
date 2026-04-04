package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryOperatorLabelCollector;

abstract class BinaryOperatorLabelCollectorTestBase<E extends Expression> extends TestBase<E> {
    BinaryOperatorLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsBinaryOperatorLabelsInPreorder() {
        assertEquals(
            List.of(
                "Conjunction", "LessThan", "Equality", "Addition", "Subtraction", "Multiplication",
                "Division", "Modulo", "Exponentiation", "Inequality", "GreaterThan", "LessThanOrEqual",
                "GreaterThanOrEqual", "Disjunction"
            ),
testSupport.sampleTraversalExpression().accept(testSupport.v.binaryOperatorLabelCollector())
        );
    }
}

class BinaryOperatorLabelCollectorTest extends BinaryOperatorLabelCollectorTestBase<Expression> {
    BinaryOperatorLabelCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
