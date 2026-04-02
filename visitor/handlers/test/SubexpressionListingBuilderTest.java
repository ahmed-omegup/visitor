package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.Negation;
import visitor.expression.VariableReference;
import visitor.handlers.SubexpressionListingBuilder;

class SubexpressionListingBuilderTest {
    @Test
    void listsLeavesBeforeContainingExpressions() {
        assertEquals(
            List.of("x", "2", "(-2)", "(x + (-2))"),
            new SubexpressionListingBuilder().handle(new Addition(new VariableReference("x"), new Negation(new Literal("2"))))
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