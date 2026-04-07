package lib.expression;

import java.util.function.Function;

public interface Visitor<R> extends ExpressionVisitor<R>, Function<Expression, R> {
    default R apply(Expression expression) {
        return expression.accept(this);
    }

}
