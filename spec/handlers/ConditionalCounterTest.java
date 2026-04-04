package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.visitors.ConditionalCounter;

abstract class ConditionalCounterTestBase<E> extends TestBase<E> {
    ConditionalCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsConditionalsAcrossTraversal() {
        assertEquals(1,testSupport.sampleTraversalExpression().accept(testSupport.v.conditionalCounter()));
        assertEquals(
            2,
factory.conditional(factory.literal("1"), factory.literal("2"), factory.conditional(factory.literal("0"), factory.literal("3"), factory.literal("4"))).accept(testSupport.v.conditionalCounter())
        );
    }
}

class ConditionalCounterTest extends ConditionalCounterTestBase<Expression> {
    ConditionalCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
