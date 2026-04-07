package lib.expression.category;

public interface ArithmeticExpression {
    <R>R accept(ArithmeticExpressionVisitor<R> visitor);
}
