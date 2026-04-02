package lib.expression;

public class GreaterThanOrEqual implements Expression { 
    GreaterThanOrEqual(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression left;
    public final Expression right;
}