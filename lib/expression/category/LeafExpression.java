package lib.expression.category;

public interface LeafExpression<E> {
    <R>R accept(LeafExpressionVisitor<R, E> visitor);
}
