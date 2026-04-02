package visitor.handlers.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import visitor.expression.Addition;
import visitor.expression.Literal;

class LiteralValueExtractorTest {
    @Test
    void parsesIntegerLiteral() {
        var extractor = TestSupport.newPackagePrivateInstance("visitor.handlers.LiteralValueExtractor");

        assertEquals(42, TestSupport.invokeHandle(extractor, new Literal("42")), "numeric literal should parse");
        assertEquals(-7, TestSupport.invokeHandle(extractor, new Literal("-7")), "negative literal should parse");
    }

    @Test
    void rejectsNonLiteralValues() {
        var extractor = TestSupport.newPackagePrivateInstance("visitor.handlers.LiteralValueExtractor");

        assertNull(TestSupport.invokeHandle(extractor, new Literal("not-a-number")), "non-numeric literal should return null");
        assertNull(TestSupport.invokeHandle(extractor, new Addition(new Literal("1"), new Literal("2"))), "non-literal expression should return null");
    }

    @Test
    void rejectsEveryOtherExpressionType() {
        var extractor = TestSupport.newPackagePrivateInstance("visitor.handlers.LiteralValueExtractor");

        for (var expression : TestSupport.sampleNonVariableExpressions()) {
            assertNull(TestSupport.invokeHandle(extractor, expression), "non-literal expression should return null for " + expression.getClass().getSimpleName());
        }
    }
}