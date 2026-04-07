package lib.visitors;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;
import lib.expressions.Expressions;

public final class LocalReduceVisitor<T> implements Consumer<Expression> {
    private final Expressions<T> values;
    private final BiFunction<T, Expression, T> reducer;
    private final ExpressionVisitor<List<Expression>> children = new ExpressionChildren();
    private final Function<Expression, T> getter;

    public LocalReduceVisitor(Expressions<T> values, BiFunction<T, Expression, T> reducer) {
        this.values = values;
        this.reducer = reducer;
        getter = new IsomorphicGetter<>(values);
    }

    private void handleThis(Expression expression) {
        new IsomorphicSetter<>(values, e -> reducer.apply(getter.apply(e), e)).accept(expression);
    }

    public void accept(Expression expression) {
        handleThis(expression);
        for (var child : expression.accept(children)) {
            this.accept(child);
        }
    }
}