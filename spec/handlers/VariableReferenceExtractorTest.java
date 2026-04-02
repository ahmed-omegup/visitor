package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;

class VariableReferenceExtractorTest {
    @Test
    void returnsVariableReference() {
        var extractor = TestSupport.newPackagePrivateInstance("lib.handlers.VariableReferenceExtractor");
        var variable = lib.expression.Expression.variableReference("threshold");

        assertSame(variable, TestSupport.invokeHandleWithMessage(extractor, variable, "expected variable"), "variable reference should be returned as-is");
    }

    @Test
    void rejectsNonVariableReference() {
        var extractor = TestSupport.newPackagePrivateInstance("lib.handlers.VariableReferenceExtractor");

        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> TestSupport.invokeHandleWithMessage(extractor, lib.expression.Expression.literal("3"), "expected variable")).getMessage());
        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> TestSupport.invokeHandleWithMessage(extractor, lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2")), "expected variable")).getMessage());
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = TestSupport.newPackagePrivateInstance("lib.handlers.VariableReferenceExtractor");

        for (var expression : TestSupport.sampleNonVariableExpressions()) {
            assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> TestSupport.invokeHandleWithMessage(extractor, expression, "expected variable")).getMessage());
        }
    }
}