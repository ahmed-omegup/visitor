package visitor.expression;

public class Expression3 implements Expression { 
    public Expression3(Expression[] list) { this.list = list; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression[] list;
}