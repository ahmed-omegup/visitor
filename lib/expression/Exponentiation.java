package lib.expression;

public class Exponentiation implements Expression { 
    Exponentiation(Expression base, Expression exponent) {
        this.base = base;
        this.exponent = exponent;
    }
    public <R>R accept(ExpressionVisitor<R> visitor) {return visitor.visit(this); } 
    public final Expression base;
    public final Expression exponent;
}