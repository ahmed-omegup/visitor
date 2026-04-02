package lib.expression;

public class LogicalNot implements Expression { 
    LogicalNot(Expression operand) { this.operand = operand; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression operand;
}