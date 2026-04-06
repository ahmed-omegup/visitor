package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalCounter;
import lib.handlers.HandlerFactory;

abstract class ConditionalCounterTestBase<E> extends TestBase<E> {
    ConditionalCounterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void countsConditionalsAcrossTraversal() {
        assertEquals(1,testSupport.v.conditionalCounter().apply(testSupport.sampleTraversalExpression()));
        assertEquals(
            2,testSupport.v.conditionalCounter().apply(factory.conditional(factory.literal("1"), factory.literal("2"), factory.conditional(factory.literal("0"), factory.literal("3"), factory.literal("4"))))
        );
    }
}

class ConditionalCounterTest extends ConditionalCounterTestBase<Expression> {
    ConditionalCounterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
