package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import lib.expression.Addition;
import lib.expression.Literal;

class LiteralValueExtractorTest {
    @Test
    void parsesIntegerLiteral() {
        var extractor = TestSupport.newPackagePrivateInstance("lib.handlers.LiteralValueExtractor");

        assertEquals(42, TestSupport.invokeHandle(extractor, lib.expression.Expression.literal("42")), "numeric literal should parse");
        assertEquals(-7, TestSupport.invokeHandle(extractor, lib.expression.Expression.literal("-7")), "negative literal should parse");
    }

    @Test
    void rejectsNonLiteralValues() {
        var extractor = TestSupport.newPackagePrivateInstance("lib.handlers.LiteralValueExtractor");

        assertNull(TestSupport.invokeHandle(extractor, lib.expression.Expression.literal("not-a-number")), "non-numeric literal should return null");
        assertNull(TestSupport.invokeHandle(extractor, lib.expression.Expression.addition(lib.expression.Expression.literal("1"), lib.expression.Expression.literal("2"))), "non-literal expression should return null");
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = TestSupport.newPackagePrivateInstance("lib.handlers.LiteralValueExtractor");

        for (var expression : TestSupport.sampleNonVariableExpressions()) {
            assertNull(TestSupport.invokeHandle(extractor, expression), "non-literal expression should return null for " + expression.getClass().getSimpleName());
        }
    }
}