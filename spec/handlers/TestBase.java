package spec.handlers;

import lib.expression.Expression;
import port.IExpressionFactory;

abstract class TestBase<E extends Expression> {
    protected final TestSupport<E> testSupport;
    protected final IExpressionFactory<E> factory;

    TestBase(TestSupport<E> testSupport) {
        this.testSupport = testSupport;
        this.factory = testSupport.factory;
    }
}
