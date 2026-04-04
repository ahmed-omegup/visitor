package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import port.IFactory;

abstract class VariableReferenceExtractorTestBase<E extends Expression> extends TestBase<E> {
    VariableReferenceExtractorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void returnsVariableReference() {
        var extractor = testSupport.v.variableReferenceExtractor();
        var variable = factory.variableReference("threshold");

        assertSame(variable, variable.accept(extractor), "variable reference should be returned as-is");
    }

    @Test
    void rejectsNonVariableReference() {
        var extractor = testSupport.v.variableReferenceExtractor();

        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> factory.literal("3").accept(extractor)).getMessage());
        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> factory.addition(factory.literal("1"), factory.literal("2")).accept(extractor)).getMessage());
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = testSupport.v.variableReferenceExtractor();

        for (var expression : testSupport.sampleNonVariableExpressions()) {
            assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> expression.accept(extractor)).getMessage());
        }
    }
}

class VariableReferenceExtractorTest extends VariableReferenceExtractorTestBase<Expression> {
    VariableReferenceExtractorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
