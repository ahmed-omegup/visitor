package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Conditional;
import lib.expression.Literal;
import lib.handlers.ConditionalPathCollector;

class ConditionalPathCollectorTest {
    @Test
    void collectsPathsToNestedConditionals() {
        assertEquals(
            List.of("root", "root.whenFalse"),
            new ConditionalPathCollector().handle(
                lib.expression.Expression.conditional(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"), lib.expression.Expression.conditional(lib.expression.Expression.literal("0"), lib.expression.Expression.literal("3"), lib.expression.Expression.literal("4")))
            )
        );
    }

    @Test
    void collectsTraversalExpressionConditionalRootPath() {
        assertEquals(List.of("root"), new ConditionalPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}