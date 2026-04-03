package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.expression.VariableReference;
import port.IFactory;

class VariableReferenceExtractorTest {
    private final IFactory factory = new Factory();
    @Test
    void returnsVariableReference() {
        var extractor = v.variableReferenceExtractor();
        var variable = factory.variableReference("threshold");

        assertSame(variable, invokeHandleWithMessage(extractor, variable, "expected variable"), "variable reference should be returned as-is");
    }

    @Test
    void rejectsNonVariableReference() {
        var extractor = v.variableReferenceExtractor();

        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> invokeHandleWithMessage(extractor, factory.literal("3"), "expected variable")).getMessage());
        assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> invokeHandleWithMessage(extractor, factory.addition(factory.literal("1"), factory.literal("2")), "expected variable")).getMessage());
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = v.variableReferenceExtractor();

        for (var expression : sampleNonVariableExpressions()) {
            assertEquals("expected variable", assertThrows(IllegalArgumentException.class, () -> invokeHandleWithMessage(extractor, expression, "expected variable")).getMessage());
        }
    }
}