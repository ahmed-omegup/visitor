package lib.expression.category;

public interface LeafExpression {
    <R>R accept(LeafExpressionVisitor<R> visitor);
}
