package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;
import visitor.expression.VariableReference;

class VariableReferenceExtractorTest {
    @Test
    void returnsVariableReference() {
        var extractor = TestSupport.newPackagePrivateInstance("visitor.handlers.VariableReferenceExtractor");
        var variable = new VariableReference("threshold");

        assertSame(variable, TestSupport.invokeHandleWithMessage(extractor, variable, "expected variable"), "variable reference should be returned as-is");
    }

    @Test
    void rejectsNonVariableReference() {
        var extractor = TestSupport.newPackagePrivateInstance("visitor.handlers.VariableReferenceExtractor");

        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> TestSupport.invokeHandleWithMessage(extractor, new Literal("3"), "expected variable")).getMessage());
        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> TestSupport.invokeHandleWithMessage(extractor, new Addition(new Literal("1"), new Literal("2")), "expected variable")).getMessage());
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = TestSupport.newPackagePrivateInstance("visitor.handlers.VariableReferenceExtractor");

        for (var expression : TestSupport.sampleNonVariableExpressions()) {
            assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> TestSupport.invokeHandleWithMessage(extractor, expression, "expected variable")).getMessage());
        }
    }
}