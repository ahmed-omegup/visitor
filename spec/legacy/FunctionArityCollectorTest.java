package spec.legacy;

import lib.expression.Expression;
import lib.legacy.FunctionArityCollector;
import lib.legacy.HandlerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.List.of;

import java.util.List;

import org.junit.jupiter.api.Test;

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
