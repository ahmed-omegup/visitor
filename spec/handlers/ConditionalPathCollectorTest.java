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
import lib.visitors.ConditionalPathCollector;
import port.IFactory;

abstract class ConditionalPathCollectorTestBase<E extends Expression> extends TestBase<E> {
    ConditionalPathCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void collectsPathsToNestedConditionals() {
        assertEquals(
            of("root", "root.whenFalse"),
factory.conditional(factory.literal("1"), factory.literal("2"), factory.conditional(factory.literal("0"), factory.literal("3"), factory.literal("4"))).accept(testSupport.v.conditionalPathCollector())
        );
    }

    @Test
    void collectsTraversalExpressionConditionalRootPath() {
        assertEquals(of("root"),testSupport.sampleTraversalExpression().accept(testSupport.v.conditionalPathCollector()));
    }
}

class ConditionalPathCollectorTest extends ConditionalPathCollectorTestBase<Expression> {
    ConditionalPathCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
