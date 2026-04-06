package spec.handlers;

import lib.expression.Expression;
import lib.handlers.HandlerFactory;
import lib.handlers.LiteralDepthSequenceBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

abstract class LiteralDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    LiteralDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void recordsLiteralDepthsInEncounterOrder() {
        assertEquals(
            of(3, 4, 4, 3, 3, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3),testSupport.v.literalDepthSequenceBuilder().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class LiteralDepthSequenceBuilderTest extends LiteralDepthSequenceBuilderTestBase<Expression> {
    LiteralDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
