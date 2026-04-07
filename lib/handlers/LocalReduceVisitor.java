package lib.handlers;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import lib.expression.*;
import lib.expressions.Expressions;
import lib.visitors.ExpressionChildren;
import lib.visitors.IsomorphicGetter;

public final class LocalReduceVisitor<T> implements Function<Expression, T> {
    private final BinaryOperator<T> reducer;
    private final ExpressionVisitor<List<Expression>> children = new ExpressionChildren();
    private final Function<Expression, T> getter;

    public LocalReduceVisitor(Expressions<T> values, BinaryOperator<T> reducer) {
        this.reducer = reducer;
        this.getter = new IsomorphicGetter<>(values);
    }

    public T apply(Expression expression) {
        var result = getter.apply(expression);
        for (var child : expression.accept(children)) {
            result = reducer.apply(result, apply(child));
        }
        return result;
    }
}