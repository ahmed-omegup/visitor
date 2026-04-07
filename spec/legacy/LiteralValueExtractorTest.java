package spec.legacy;

import lib.expression.Expression;
import lib.expression.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;
import lib.legacy.HandlerFactory;

abstract class LiteralValueExtractorTestBase<E> extends TestBase<E> {
    LiteralValueExtractorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


        @Test
    void parsesIntegerLiteral() {
        var extractor = testSupport.v.literalValueExtractor();

        assertEquals(42,extractor.apply(factory.literal("42")), "numeric literal should parse");
        assertEquals(-7,extractor.apply(factory.literal("-7")), "negative literal should parse");
    }

    @Test
    void rejectsNonLiteralValues() {
        var extractor = testSupport.v.literalValueExtractor();

        assertNull(extractor.apply(factory.literal("not-a-number")), "non-numeric literal should return null");
        assertNull(extractor.apply(factory.addition(factory.literal("1"), factory.literal("2"))), "non-literal expression should return null");
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = testSupport.v.literalValueExtractor();

        for (var expression : testSupport.sampleNonVariableExpressions()) {
            assertNull(extractor.apply(expression), "non-literal expression should return null for " + typeName(expression));
        }
    }
}

class LiteralValueExtractorTest extends LiteralValueExtractorTestBase<Expression> {
    LiteralValueExtractorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
