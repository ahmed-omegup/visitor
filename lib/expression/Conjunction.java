package lib.expression;

public class Conjunction implements Expression { 
    Conjunction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public <R>R accept(ExpressionVisitor<R> visitor) {return visitor.visit(this); } 
    public final Expression left;
    public final Expression right;
}