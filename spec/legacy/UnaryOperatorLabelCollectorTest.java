package spec.legacy;

import lib.expression.Expression;
import lib.legacy.HandlerFactory;
import lib.legacy.UnaryOperatorLabelCollector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

abstract class UnaryOperatorLabelCollectorTestBase<E> extends TestBase<E> {
    UnaryOperatorLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsUnaryOperatorLabelsInPreorder() {
        assertEquals(of("LogicalNot", "Negation"),testSupport.v.unaryOperatorLabelCollector().apply(testSupport.sampleTraversalExpression()));
    }
}

class UnaryOperatorLabelCollectorTest extends UnaryOperatorLabelCollectorTestBase<Expression> {
    UnaryOperatorLabelCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
