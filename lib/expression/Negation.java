package lib.expression;

public class Negation implements Expression { 
    Negation(Expression operand) { this.operand = operand; } 
    public <R>R accept(Visitor1<R> visitor) {return visitor.visit(this); } 
    public final Expression operand;
}