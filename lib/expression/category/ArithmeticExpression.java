package lib.expression.category;

public interface ArithmeticExpression<E> {
    <R>R accept(ArithmeticExpressionVisitor<R, E> visitor);
}
