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

        assertEquals(42, invokeHandle(extractor, factory.literal("42")), "numeric literal should parse");
        assertEquals(-7, invokeHandle(extractor, factory.literal("-7")), "negative literal should parse");
    }

    @Test
    void rejectsNonLiteralValues() {
        var extractor = v.literalValueExtractor();

        assertNull(invokeHandle(extractor, factory.literal("not-a-number")), "non-numeric literal should return null");
        assertNull(invokeHandle(extractor, factory.addition(factory.literal("1"), factory.literal("2"))), "non-literal expression should return null");
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = v.literalValueExtractor();

        for (var expression : sampleNonVariableExpressions()) {
            assertNull(invokeHandle(extractor, expression), "non-literal expression should return null for " + expression.getClass().getSimpleName());
        }
    }
}