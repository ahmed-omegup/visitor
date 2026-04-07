package lib.expression.category;

public interface OddExpression {
    <R>R accept(OddExpressionVisitor<R> visitor);    
}
