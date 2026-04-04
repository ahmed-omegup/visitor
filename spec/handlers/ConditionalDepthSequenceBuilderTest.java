package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.visitors.ConditionalDepthSequenceBuilder;

abstract class ConditionalDepthSequenceBuilderTestBase<E extends Expression> extends TestBase<E> {
    ConditionalDepthSequenceBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void recordsConditionalDepthsInEncounterOrder() {
        assertEquals(of(0),testSupport.sampleTraversalExpression().accept(testSupport.v.conditionalDepthSequenceBuilder()));
        assertEquals(
            of(0, 1),
factory.conditional(factory.literal("1"), factory.conditional(factory.literal("0"), factory.literal("2"), factory.literal("3")), factory.literal("4")).accept(testSupport.v.conditionalDepthSequenceBuilder())
        );
    }
}

class ConditionalDepthSequenceBuilderTest extends ConditionalDepthSequenceBuilderTestBase<Expression> {
    ConditionalDepthSequenceBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
