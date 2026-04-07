package lib.expression.category;

public interface LogicalExpression {
    <R>R accept(LogicalExpressionVisitor<R> visitor);
}
