package spec.visitors;

import static java.util.List.of;

import lib.expression.*;
import lib.expressions.ExpressionClassNames;
import lib.expressions.Expressions;
import lib.visitors.IsomorphicGetter;

final class TestSupport {
    final Factory factory = new Factory();
    final Expressions<String> values = new ExpressionClassNames();
    final IsomorphicGetter<String> typeNames = new IsomorphicGetter<>(new ExpressionClassNames());

    Expression sampleTraversalExpression() {
        return factory.addition(
            factory.literal("1"),
            factory.functionCall(
                factory.variableReference("sum"),
                of(factory.negation(factory.literal("2")), factory.variableReference("x"))
            )
        );
    }
}