package lib.expression.category;

public interface ComparisonExpression {
    <R>R accept(ComparisonExpressionVisitor<R> visitor);
}
