package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Conditional;
import visitor.expression.Literal;
import visitor.handlers.ConditionalPathCollector;

class ConditionalPathCollectorTest {
    @Test
    void collectsPathsToNestedConditionals() {
        assertEquals(
            List.of("root", "root.whenFalse"),
            new ConditionalPathCollector().handle(
                new Conditional(new Literal("1"), new Literal("2"), new Conditional(new Literal("0"), new Literal("3"), new Literal("4")))
            )
        );
    }

    @Test
    void collectsTraversalExpressionConditionalRootPath() {
        assertEquals(List.of("root"), new ConditionalPathCollector().handle(TestSupport.sampleTraversalExpression()));
    }
}