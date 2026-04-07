package lib.expression.category;

public interface BinaryArithmeticExpression {
    <R>R accept(BinaryArithmeticExpressionVisitor<R> visitor);
}
