package lib.expression.category;

public interface ComparisonExpression<E> {
    <R>R accept(ComparisonExpressionVisitor<R, E> visitor);
}
