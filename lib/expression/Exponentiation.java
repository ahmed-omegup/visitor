package lib.expression;

public class Exponentiation<E> implements Expression<E> {
    Exponentiation(E base, E exponent) {
        this.base = base;
        this.exponent = exponent;
    }
    public <R>R accept(EExpressionVisitor<R, E> visitor) {return visitor.visit(this); }
    public final E base;
    public final E exponent;
}