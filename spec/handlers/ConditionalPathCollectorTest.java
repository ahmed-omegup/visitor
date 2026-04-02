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
                lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("1"), lib.expression.ExpressionFactory.literal("2"), lib.expression.ExpressionFactory.conditional(lib.expression.ExpressionFactory.literal("0"), lib.expression.ExpressionFactory.literal("3"), lib.expression.ExpressionFactory.literal("4")))
            )
        );
    }

    @Test
    void collectsTraversalExpressionConditionalRootPath() {
        assertEquals(List.of("root"), new ConditionalPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}