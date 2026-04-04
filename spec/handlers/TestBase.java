package spec.handlers;

import port.IExpressionFactory;

abstract class TestBase<E> {
    protected final TestSupport<E> testSupport;
    protected final IExpressionFactory<E> factory;

    TestBase(TestSupport<E> testSupport) {
        this.testSupport = testSupport;
        this.factory = testSupport.factory;
    }
}
