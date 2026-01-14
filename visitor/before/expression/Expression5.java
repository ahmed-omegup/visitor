package visitor.before.expression;

public class Expression5 implements Expression { 
    public Expression5(Expression[] list) { this.list = list; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression[] list;
}