package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.Negation;
import lib.expression.VariableReference;
import lib.visitors.SubexpressionListingBuilder;
import port.IFactory;

abstract class SubexpressionListingBuilderTestBase<E extends Expression> extends TestBase<E> {
    SubexpressionListingBuilderTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void listsLeavesBeforeContainingExpressions() {
        assertEquals(
            of("x", "2", "(-2)", "(x + (-2))"),
factory.addition(factory.variableReference("x"), factory.negation(factory.literal("2"))).accept(testSupport.v.subexpressionListingBuilder())
        );
    }

    @Test
    void listsTraversalSubexpressionsIncludingFinalRoot() {
        var values =testSupport.sampleTraversalExpression().accept(testSupport.v.subexpressionListingBuilder());

        assertTrue(values.contains("(7 - 2)"));
        assertTrue(values.stream().anyMatch(value -> value.startsWith("f(")));
        assertTrue(values.get(values.size() - 1).contains(" ? "));
    }
}

class SubexpressionListingBuilderTest extends SubexpressionListingBuilderTestBase<Expression> {
    SubexpressionListingBuilderTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
