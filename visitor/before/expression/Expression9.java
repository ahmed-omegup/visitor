package visitor.before.expression;

public class Expression9 implements Expression { 
    public Expression9(Expression[] list) { this.list = list; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression[] list;
}