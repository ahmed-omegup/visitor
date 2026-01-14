package visitor.before.expression;

public class Expression8 implements Expression { 
    public Expression8(Expression[] list) { this.list = list; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression[] list;
}