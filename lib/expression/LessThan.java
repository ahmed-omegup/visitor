package lib.expression;

public class LessThan<E> implements Expression<E> {
    LessThan(E left, E right) {
        this.left = left;
        this.right = right;
    }
    public <R>R accept(EExpressionVisitor<R, E> visitor) {return visitor.visit(this); }
    public final E left;
    public final E right;
}