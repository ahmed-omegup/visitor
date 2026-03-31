package visitor.expression;

public class LogicalNot implements Expression { 
    public LogicalNot(Expression operand) { this.operand = operand; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression operand;
}