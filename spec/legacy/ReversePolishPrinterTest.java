package spec.legacy;

import lib.expression.Expression;
import lib.legacy.HandlerFactory;
import lib.legacy.ReversePolishPrinter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

abstract class ReversePolishPrinterTestBase<E> extends TestBase<E> {
    ReversePolishPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void rendersTraversalExpressionInReversePolishNotation() {
        assertEquals(
            "x 10 < 1 0 == ! && 7 2 - 8 2 / 9 4 % * + f 2 3 ^ 5 6 != 7 1 > 2 2 <= 3 3 >= 0 1 || 4 neg call ?:",testSupport.v.reversePolishPrinter().apply(testSupport.sampleTraversalExpression())
        );
    }
}

class ReversePolishPrinterTest extends ReversePolishPrinterTestBase<Expression> {
    ReversePolishPrinterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
