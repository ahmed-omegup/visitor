package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.PrefixNotationPrinter;

abstract class PrefixNotationPrinterTestBase<E> extends TestBase<E> {
    PrefixNotationPrinterTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void rendersTraversalExpressionInPrefixNotation() {
        assertEquals(
            "(?: (&& (< x 10) (! (== 1 0))) (+ (- 7 2) (* (/ 8 2) (% 9 4))) (call f (^ 2 3) (!= 5 6) (> 7 1) (<= 2 2) (>= 3 3) (|| 0 1) (neg 4)))",
testSupport.sampleTraversalExpression().accept(testSupport.v.prefixNotationPrinter())
        );
    }
}

class PrefixNotationPrinterTest extends PrefixNotationPrinterTestBase<Expression> {
    PrefixNotationPrinterTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
