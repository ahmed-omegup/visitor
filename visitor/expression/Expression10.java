package visitor.expression;

public class Expression10 implements Expression { 
    public Expression10(Expression[] list) { this.list = list; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression[] list;
}