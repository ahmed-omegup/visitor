package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.UnaryNodeDepthSequenceBuilder;

abstract class UnaryNodeDepthSequenceBuilderTestBase<E extends Expression> extends TestBase<E> {
    UnaryNodeDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void recordsUnaryNodeDepths() {
        assertEquals(List.of(2, 2),testSupport.sampleTraversalExpression().accept(testSupport.v.unaryNodeDepthSequenceBuilder()));
    }
}

class UnaryNodeDepthSequenceBuilderTest extends UnaryNodeDepthSequenceBuilderTestBase<Expression> {
    UnaryNodeDepthSequenceBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
