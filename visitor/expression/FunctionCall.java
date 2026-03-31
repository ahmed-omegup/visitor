package visitor.expression;

public class FunctionCall implements Expression { 
    public FunctionCall(Expression callee, Expression... arguments) {
        this.callee = callee;
        this.arguments = arguments;
    }
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression callee;
    public final Expression[] arguments;
}