package lib.visitors;

import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.Expression;

public final class RecursiveVisitor implements Consumer<Expression> {
    private final Consumer<Expression> visitor;
    private final Function<Expression, java.util.List<Expression>> children;

    public RecursiveVisitor(Consumer<Expression> visitor) {
        this(visitor, new ExpressionChildren());
    }

    public RecursiveVisitor(Consumer<Expression> visitor, Function<Expression, java.util.List<Expression>> children) {
        this.visitor = visitor;
        this.children = children;
    }

    public void accept(Expression expression) {
        visitor.accept(expression);
        for (var child : children.apply(expression)) {
            accept(child);
        }
    }
}