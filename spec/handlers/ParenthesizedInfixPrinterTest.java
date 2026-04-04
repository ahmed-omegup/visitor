package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.ParenthesizedInfixPrinter;

abstract class ParenthesizedInfixPrinterTestBase<E extends Expression> extends TestBase<E> {
    ParenthesizedInfixPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void rendersAllExpressionTypesWithParentheses() {
        var printer = testSupport.v.parenthesizedInfixPrinter();

        assertEquals(
            "(((x < 10) && (!(1 == 0))) ? ((7 - 2) + ((8 / 2) * (9 % 4))) : f((2 ^ 3), (5 != 6), (7 > 1), (2 <= 2), (3 >= 3), (0 || 1), (-4)))",
testSupport.sampleTraversalExpression().accept(printer)
        );
    }
}

class ParenthesizedInfixPrinterTest extends ParenthesizedInfixPrinterTestBase<Expression> {
    ParenthesizedInfixPrinterTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
