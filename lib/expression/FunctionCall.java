package lib.expression;

import java.util.List;

public class FunctionCall implements Expression { 
    FunctionCall(Expression callee, List<Expression> arguments) {
        this.callee = callee;
        this.arguments = arguments;
    }
    public <R>R accept(Visitor1<R> visitor) {return visitor.visit(this); } 
    public final Expression callee;
    public final List<Expression> arguments;
}