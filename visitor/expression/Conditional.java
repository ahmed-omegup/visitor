package visitor.expression;

public class Conditional implements Expression { 
    public Conditional(Expression condition, Expression whenTrue, Expression whenFalse) {
        this.condition = condition;
        this.whenTrue = whenTrue;
        this.whenFalse = whenFalse;
    }
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression condition;
    public final Expression whenTrue;
    public final Expression whenFalse;
}