package spec.handlers;

import lib.expression.Expression;
import lib.visitors.VisitorFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.FunctionArityCollector;

abstract class FunctionArityCollectorTestBase<E extends Expression> extends TestBase<E> {
    FunctionArityCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsFunctionAritiesInTraversalOrder() {
        assertEquals(List.of(7),testSupport.sampleTraversalExpression().accept(testSupport.v.functionArityCollector()));
    }
}

class FunctionArityCollectorTest extends FunctionArityCollectorTestBase<Expression> {
    FunctionArityCollectorTest() {
        super(new TestSupport<>(new VisitorFactory()));
    }
}
