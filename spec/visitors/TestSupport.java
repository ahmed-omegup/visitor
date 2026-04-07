package spec.visitors;

import static java.util.List.of;

import lib.expression.*;
import lib.expressions.ExpressionClassNames;
import lib.expressions.Expressions;
import port.ICleanHandlerFactory;
import port.IExpressionFactory;

final class TestSupport<E> {
    final ICleanHandlerFactory<E> v;
    final IExpressionFactory<E> factory;
    final Expressions<String> values = new ExpressionClassNames();

    TestSupport(ICleanHandlerFactory<E> v) {
        this.v = v;
        this.factory = v.expressionFactory();
    }

    E sampleTraversalExpression() {
        return factory.addition(
            factory.literal("1"),
            factory.functionCall(
                factory.variableReference("sum"),
                of(factory.negation(factory.literal("2")), factory.variableReference("x"))
            )
        );
    }
}