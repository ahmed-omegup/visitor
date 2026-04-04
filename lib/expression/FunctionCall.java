package lib.expression;

import java.util.List;

public class FunctionCall implements Expression { 
    FunctionCall(Expression callee, List<Expression> arguments) {
        this.callee = callee;
        this.arguments = arguments.toArray(Expression[]::new);
    }
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final Expression callee;
    public final Expression[] arguments;
}