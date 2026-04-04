package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.LiteralCollector;

abstract class LiteralCollectorTestBase<E> extends TestBase<E> {
    LiteralCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsLiteralsInTraversalOrder() {
        assertEquals(
            of("10", "1", "0", "7", "2", "8", "2", "9", "4", "2", "3", "5", "6", "7", "1", "2", "2", "3", "3", "0", "1", "4"),
testSupport.sampleTraversalExpression().accept(testSupport.v.literalCollector())
        );
    }
}

class LiteralCollectorTest extends LiteralCollectorTestBase<Expression> {
    LiteralCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
