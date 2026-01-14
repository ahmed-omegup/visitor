package visitor.before.expression;

public class Expression17 implements Expression { 
    public Expression17(Expression[] list) { this.list = list; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression[] list;
}