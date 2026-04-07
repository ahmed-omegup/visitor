package lib.handlers;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;
import lib.expressions.Expressions;
import lib.visitors.ExpressionChildren;
import lib.visitors.IsomorphicGetter;
import lib.visitors.IsomorphicSetter;

public final class LocalReduceVisitor<T> implements Consumer<Expression> {
    private final ExpressionVisitor<List<Expression>> children = new ExpressionChildren();
    private final Consumer<Expression> setter;

    public LocalReduceVisitor(Expressions<T> values, BiFunction<T, Expression, T> reducer) {
        var getter = new IsomorphicGetter<>(values);
        this.setter = new IsomorphicSetter<>(values, e -> reducer.apply(getter.apply(e), e));
    }

    public void accept(Expression expression) {
        setter.accept(expression);
        for (var child : expression.accept(children)) {
            accept(child);
        }
    }
}