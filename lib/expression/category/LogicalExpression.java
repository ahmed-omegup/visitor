package lib.expression.category;

public interface LogicalExpression<E> {
    <R>R accept(LogicalExpressionVisitor<R, E> visitor);
}
