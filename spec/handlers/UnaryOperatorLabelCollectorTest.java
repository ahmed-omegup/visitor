package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.UnaryOperatorLabelCollector;

abstract class UnaryOperatorLabelCollectorTestBase<E extends Expression> extends TestBase<E> {
    UnaryOperatorLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsUnaryOperatorLabelsInPreorder() {
        assertEquals(List.of("LogicalNot", "Negation"),testSupport.sampleTraversalExpression().accept(testSupport.v.unaryOperatorLabelCollector()));
    }
}

class UnaryOperatorLabelCollectorTest extends UnaryOperatorLabelCollectorTestBase<Expression> {
    UnaryOperatorLabelCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
