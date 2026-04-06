package lib.visitors;

import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.Expression;


final class RecursiveExpression implements Consumer<Expression> {
    private final Consumer<Expression> handler;
    private final Function<Expression, Iterable<Expression>> childrenExtractor = new ExpressionChildren();

    RecursiveExpression(Consumer<Expression> handler) {
        this.handler = handler;
    }

    public void accept(Expression expression) {
        handler.accept(expression);
        for (var child : childrenExtractor.apply(expression)) {
            accept(child);
        }
    }
}