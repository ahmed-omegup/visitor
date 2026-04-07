package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.legacy.ConditionalPathCollector;
import lib.legacy.HandlerFactory;

abstract class ConditionalPathCollectorTestBase<E> extends TestBase<E> {
    ConditionalPathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsPathsToNestedConditionals() {
        assertEquals(
            of("root", "root.whenFalse"),testSupport.v.conditionalPathCollector().apply(factory.conditional(factory.literal("1"), factory.literal("2"), factory.conditional(factory.literal("0"), factory.literal("3"), factory.literal("4"))))
        );
    }

    @Test
    void collectsTraversalExpressionConditionalRootPath() {
        assertEquals(of("root"),testSupport.v.conditionalPathCollector().apply(testSupport.sampleTraversalExpression()));
    }
}

class ConditionalPathCollectorTest extends ConditionalPathCollectorTestBase<Expression> {
    ConditionalPathCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
