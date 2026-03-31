package visitor.expression;

public class Negation implements Expression { 
    public Negation(Expression operand) { this.operand = operand; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression operand;
}