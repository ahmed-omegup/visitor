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
import port.IFactory;

class SubexpressionListingBuilderTest {
    private final IFactory factory = new Factory();
    @Test
    void listsLeavesBeforeContainingExpressions() {
        assertEquals(
            List.of("x", "2", "(-2)", "(x + (-2))"),
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(TestSupport.handlers().subexpressionListingBuilder())
        );
    }

    @Test
    void listsTraversalSubexpressionsIncludingFinalRoot() {
        var values =TestSupport.sampleTraversalExpression().accept(TestSupport.handlers().subexpressionListingBuilder());

        assertTrue(values.contains("(7 - 2)"));
        assertTrue(values.stream().anyMatch(value -> value.startsWith("f(")));
        assertTrue(values.get(values.size() - 1).contains(" ? "));
    }
}