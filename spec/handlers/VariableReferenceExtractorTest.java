package spec.handlers;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.handlers.HandlerFactory;

abstract class VariableReferenceExtractorTestBase<E> extends TestBase<E> {
    VariableReferenceExtractorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void returnsVariableReference() {
        var extractor = testSupport.v.variableReferenceExtractor();
        var variable = factory.variableReference("threshold");

        assertSame(variable,extractor.apply(variable), "variable reference should be returned as-is");
    }

    @Test
    void rejectsNonVariableReference() {
        var extractor = testSupport.v.variableReferenceExtractor();

        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () ->extractor.apply(factory.literal("3"))).getMessage());
        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () ->extractor.apply(factory.addition(factory.literal("1"), factory.literal("2")))).getMessage());
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = testSupport.v.variableReferenceExtractor();

        for (var expression : testSupport.sampleNonVariableExpressions()) {
            assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () ->extractor.apply(expression)).getMessage());
        }
    }
}

class VariableReferenceExtractorTest extends VariableReferenceExtractorTestBase<Expression> {
    VariableReferenceExtractorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
