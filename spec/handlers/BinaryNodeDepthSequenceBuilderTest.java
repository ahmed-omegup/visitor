package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.BinaryNodeDepthSequenceBuilder;

abstract class BinaryNodeDepthSequenceBuilderTestBase<E extends Expression> extends TestBase<E> {
    BinaryNodeDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void recordsBinaryNodeDepths() {
        assertEquals(
            of(1, 2, 3, 1, 2, 2, 3, 3, 2, 2, 2, 2, 2, 2),
testSupport.sampleTraversalExpression().accept(testSupport.v.binaryNodeDepthSequenceBuilder())
        );
    }
}

class BinaryNodeDepthSequenceBuilderTest extends BinaryNodeDepthSequenceBuilderTestBase<Expression> {
    BinaryNodeDepthSequenceBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
