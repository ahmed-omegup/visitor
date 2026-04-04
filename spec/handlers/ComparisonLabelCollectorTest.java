package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonLabelCollector;

abstract class ComparisonLabelCollectorTestBase<E> extends TestBase<E> {
    ComparisonLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsComparisonLabelsInPreorder() {
        assertEquals(
            of("LessThan", "Equality", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual"),
testSupport.sampleTraversalExpression().accept(testSupport.v.comparisonLabelCollector())
        );
    }
}

class ComparisonLabelCollectorTest extends ComparisonLabelCollectorTestBase<Expression> {
    ComparisonLabelCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
