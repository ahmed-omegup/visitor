package spec.handlers;

import static spec.handlers.TestSupport.*;

import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import port.IFactory;

class LiteralValueExtractorTest {
    private final IFactory factory = new Factory();
    @Test
    void parsesIntegerLiteral() {
        var extractor = v.literalValueExtractor();

        assertEquals(42, factory.literal("42").accept(extractor), "numeric literal should parse");
        assertEquals(-7, factory.literal("-7").accept(extractor), "negative literal should parse");
    }

    @Test
    void rejectsNonLiteralValues() {
        var extractor = v.literalValueExtractor();

        assertNull(factory.literal("not-a-number").accept(extractor), "non-numeric literal should return null");
        assertNull(factory.addition(factory.literal("1"), factory.literal("2")).accept(extractor), "non-literal expression should return null");
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = v.literalValueExtractor();

        for (var expression : sampleNonVariableExpressions()) {
            assertNull(expression.accept(extractor), "non-literal expression should return null for " + expression.getClass().getSimpleName());
        }
    }
}