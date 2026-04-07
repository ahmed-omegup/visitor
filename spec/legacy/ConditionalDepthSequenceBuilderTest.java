package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.legacy.ConditionalDepthSequenceBuilder;
import lib.legacy.HandlerFactory;

abstract class ConditionalDepthSequenceBuilderTestBase<E> extends TestBase<E> {
    ConditionalDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsConditionalDepthsInEncounterOrder() {
        assertEquals(of(0),testSupport.v.conditionalDepthSequenceBuilder().apply(testSupport.sampleTraversalExpression()));
        assertEquals(
            of(0, 1),testSupport.v.conditionalDepthSequenceBuilder().apply(factory.conditional(factory.literal("1"), factory.conditional(factory.literal("0"), factory.literal("2"), factory.literal("3")), factory.literal("4")))
        );
    }
}

class ConditionalDepthSequenceBuilderTest extends ConditionalDepthSequenceBuilderTestBase<Expression> {
    ConditionalDepthSequenceBuilderTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
