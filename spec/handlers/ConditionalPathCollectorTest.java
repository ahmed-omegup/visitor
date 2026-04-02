package spec.handlers;

import static lib.expression.Factory.*;

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
                conditional(literal("1"), literal("2"), conditional(literal("0"), literal("3"), literal("4")))
            )
        );
    }

    @Test
    void collectsTraversalExpressionConditionalRootPath() {
        assertEquals(List.of("root"), new ConditionalPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}