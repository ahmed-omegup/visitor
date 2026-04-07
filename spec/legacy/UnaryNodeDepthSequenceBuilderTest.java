package spec.legacy;

import lib.expression.Expression;
import lib.legacy.HandlerFactory;
import lib.legacy.UnaryNodeDepthSequenceBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

abstract class UnaryNodeDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    UnaryNodeDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void recordsUnaryNodeDepths() {
        assertEquals(of(2, 2),testSupport.v.unaryNodeDepthSequenceBuilder().apply(testSupport.sampleTraversalExpression()));
    }
}

class UnaryNodeDepthSequenceBuilderTest extends UnaryNodeDepthSequenceBuilderTestBase<Expression> {
    UnaryNodeDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
