package spec.handlers;

import lib.expression.Expression;
import lib.handlers.BooleanOperatorLabelCollector;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

abstract class BooleanOperatorLabelCollectorTestBase<E> extends TestBase<E> {
    BooleanOperatorLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsBooleanOperatorLabelsInPreorder() {
        assertEquals(of("Conjunction", "LogicalNot", "Disjunction"),testSupport.v.booleanOperatorLabelCollector().apply(testSupport.sampleTraversalExpression()));
    }
}

class BooleanOperatorLabelCollectorTest extends BooleanOperatorLabelCollectorTestBase<Expression> {
    BooleanOperatorLabelCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
