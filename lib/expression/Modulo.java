package lib.expression;

public class Modulo implements Expression { 
    Modulo(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    public <R>R accept(Visitor1<R> visitor) {return visitor.visit(this); } 
    public final Expression left;
    public final Expression right;
}