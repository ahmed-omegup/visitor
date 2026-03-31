package visitor.expression;

public class Exponentiation implements Expression { 
    public Exponentiation(Expression base, Expression exponent) {
        this.base = base;
        this.exponent = exponent;
    }
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression base;
    public final Expression exponent;
}