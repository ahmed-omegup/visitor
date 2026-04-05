package lib.expression;

public class Division implements Expression { 
    Division(Expression dividend, Expression divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
    }
    public <R>R accept(Visitor1<R> visitor) {return visitor.visit(this); } 
    public final Expression dividend;
    public final Expression divisor;
}