package lib.handlers;

import java.util.function.Function;

import lib.expression.Expression;
import lib.expressions.ExpressionClassNames;
import lib.visitors.IsomorphicGetter;

public final class ExpressionClassNameExtractor implements Function<Expression, String> {
    private final Function<Expression, String> extractor = new IsomorphicGetter<>(new ExpressionClassNames());

    public String apply(Expression expression) {
        return extractor.apply(expression);
    }
}