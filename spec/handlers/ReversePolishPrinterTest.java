package spec.handlers;

import static spec.handlers.TestSupport.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lib.visitors.ReversePolishPrinter;

class ReversePolishPrinterTest {
    @Test
    void rendersTraversalExpressionInReversePolishNotation() {
        assertEquals(
            "x 10 < 1 0 == ! && 7 2 - 8 2 / 9 4 % * + f 2 3 ^ 5 6 != 7 1 > 2 2 <= 3 3 >= 0 1 || 4 neg call ?:",
sampleTraversalExpression().accept(v.reversePolishPrinter())
        );
    }
}