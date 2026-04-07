package lib.visitors;

import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.Visitor1;

public final class ExpressionEvaluator<R> implements Function<Expression, R> {
    private final Visitor1<R> visitor;

    public ExpressionEvaluator(Visitor1<R> visitor) {
        this.visitor = visitor;
    }

    public R apply(Expression expression) {
        return expression.accept(visitor);
    }
}