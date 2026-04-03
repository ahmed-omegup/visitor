package spec.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.CompactInfixPrinter;

class CompactInfixPrinterTest {
    @Test
    void rendersAllExpressionTypesInCompactInfixForm() {
        var printer = TestSupport.handlers().compactInfixPrinter();

        assertEquals(
            "x < 10 && !1 == 0 ? 7 - 2 + 8 / 2 * 9 % 4 : f(2 ^ 3, 5 != 6, 7 > 1, 2 <= 2, 3 >= 3, 0 || 1, -4)",
TestSupport.sampleTraversalExpression().accept(printer)
        );
    }
}