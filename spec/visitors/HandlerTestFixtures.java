package spec.visitors;

import lib.expression.ExpressionV1;
import lib.expression.ExpressionV2;
import lib.handlers.HandlerFactory;
import lib.handlers.HandlerFactory2;
import port.IHandlerFactory1;
import port.IHandlerFactory2;

final class HandlerTestFixtures {
    private HandlerTestFixtures() {
    }

    static IHandlerFactory1<ExpressionV1> v1Handler() {
        return new HandlerFactory();
    }

    static IHandlerFactory2<ExpressionV2> v2Handler() {
        return new HandlerFactory2();
    }

    static TestSupport<ExpressionV1> v1Support() {
        return new TestSupport<>(v1Handler());
    }

    static TestSupport<ExpressionV2> v2Support() {
        return new TestSupport<>(v2Handler());
    }

    static TestSupport2<ExpressionV2> v2Support2() {
        return new TestSupport2<>(v2Handler());
    }
}