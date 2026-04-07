package lib.expression.category;

public interface CategoryExpression {
    <R>R accept(CategoryExpressionVisitor<R> visitor);
}