package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.visitors.ConditionalCounter;
import port.IFactory;

class ConditionalCounterTest {
    private final IFactory factory = new Factory();
    @Test
    void countsConditionalsAcrossTraversal() {
        assertEquals(1,TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().conditionalCounter()));
        assertEquals(
            2,
factory.conditional(factory.literal("1"), factory.literal("2"), factory.conditional(factory.literal("0"), factory.literal("3"), factory.literal("4"))).accept(TestSupport.handlers().conditionalCounter())
        );
    }
}