package spec.handlers;

import lib.expression.Expression;
import lib.visitors.HandlerFactory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.visitors.FunctionArityCollector;

abstract class FunctionArityCollectorTestBase<E> extends TestBase<E> {
    FunctionArityCollectorTestBase(TestSupport<E> testSupport) {
        super(testSupport);
    }


    @Test
    void collectsFunctionAritiesInTraversalOrder() {
        assertEquals(of(7),testSupport.v.functionArityCollector().apply(testSupport.sampleTraversalExpression()));
    }
}

class FunctionArityCollectorTest extends FunctionArityCollectorTestBase<Expression> {
    FunctionArityCollectorTest() {
        super(new TestSupport<>(new HandlerFactory()));
    }
}
