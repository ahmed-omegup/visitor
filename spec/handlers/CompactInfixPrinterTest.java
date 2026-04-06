package spec.handlers;

import lib.expression.Expression;
import lib.handlers.CompactInfixPrinter;
import lib.handlers.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class CompactInfixPrinterTestBase<E> extends TestBase<E> {
    CompactInfixPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void rendersAllExpressionTypesInCompactInfixForm() {
        var printer = testSupport.v.compactInfixPrinter();

        assertEquals(
            "x < 10 && !1 == 0 ? 7 - 2 + 8 / 2 * 9 % 4 : f(2 ^ 3, 5 != 6, 7 > 1, 2 <= 2, 3 >= 3, 0 || 1, -4)",printer.apply(testSupport.sampleTraversalExpression())
        );
    }
}

class CompactInfixPrinterTest extends CompactInfixPrinterTestBase<Expression> {
    CompactInfixPrinterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
