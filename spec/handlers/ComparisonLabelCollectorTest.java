package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.ComparisonLabelCollector;

abstract class ComparisonLabelCollectorTestBase<E extends Expression> extends TestBase<E> {
    ComparisonLabelCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsComparisonLabelsInPreorder() {
        assertEquals(
            List.of("LessThan", "Equality", "Inequality", "GreaterThan", "LessThanOrEqual", "GreaterThanOrEqual"),
testSupport.sampleTraversalExpression().accept(testSupport.v.comparisonLabelCollector())
        );
    }
}

class ComparisonLabelCollectorTest extends ComparisonLabelCollectorTestBase<Expression> {
    ComparisonLabelCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
