package spec.handlers;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.handlers.SubexpressionListingBuilder;

class SubexpressionListingBuilderTest {
    private final Factory factory = new Factory();
    @Test
    void listsLeavesBeforeContainingExpressions() {
        assertEquals(
            List.of("x", "2", "(-2)", "(x + (-2))"),
            new SubexpressionListingBuilder().handle(factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))))
        );
    }

    @Test
    void listsTraversalSubexpressionsIncludingFinalRoot() {
        var values = new SubexpressionListingBuilder().handle(TestSupport.sampleTraversalExpression());

        assertTrue(values.contains("(7 - 2)"));
        assertTrue(values.stream().anyMatch(value -> value.startsWith("f(")));
        assertTrue(values.get(values.size() - 1).contains(" ? "));
    }
}