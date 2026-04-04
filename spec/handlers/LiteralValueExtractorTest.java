package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;

abstract class LiteralValueExtractorTestBase<E> extends TestBase<E> {
    LiteralValueExtractorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void parsesIntegerLiteral() {
        var extractor = testSupport.v.literalValueExtractor();

        assertEquals(42, factory.literal("42").accept(extractor), "numeric literal should parse");
        assertEquals(-7, factory.literal("-7").accept(extractor), "negative literal should parse");
    }

    @Test
    void rejectsNonLiteralValues() {
        var extractor = testSupport.v.literalValueExtractor();

        assertNull(factory.literal("not-a-number").accept(extractor), "non-numeric literal should return null");
        assertNull(factory.addition(factory.literal("1"), factory.literal("2")).accept(extractor), "non-literal expression should return null");
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = testSupport.v.literalValueExtractor();

        for (var expression : testSupport.sampleNonVariableExpressions()) {
            assertNull(expression.accept(extractor), "non-literal expression should return null for " + expression.getClass().getSimpleName());
        }
    }
}

class LiteralValueExtractorTest extends LiteralValueExtractorTestBase<Expression> {
    LiteralValueExtractorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
