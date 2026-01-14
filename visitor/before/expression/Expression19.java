package visitor.before.expression;

public class Expression19 implements Expression { 
    public Expression19(Expression[] list) { this.list = list; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression[] list;
}