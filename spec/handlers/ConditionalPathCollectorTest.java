package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalPathCollector;

class ConditionalPathCollectorTest {
    private final Factory factory = new Factory();
    @Test
    void collectsPathsToNestedConditionals() {
        assertEquals(
            List.of("root", "root.whenFalse"),
            new ConditionalPathCollector().handle(
                factory.conditional(factory.literal("1"), factory.literal("2"), factory.conditional(factory.literal("0"), factory.literal("3"), factory.literal("4")))
            )
        );
    }

    @Test
    void collectsTraversalExpressionConditionalRootPath() {
        assertEquals(List.of("root"), new ConditionalPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}