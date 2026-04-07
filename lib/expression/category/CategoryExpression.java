package lib.expression.category;

public interface CategoryExpression<E> {
    <R>R accept(CategoryExpressionVisitor<R, E> visitor);
}