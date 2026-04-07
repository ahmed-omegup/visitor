package lib.expression;

public class VariableReference implements Expression { 
    VariableReference(String name) { this.name = name; } 
    public <R>R accept(ExpressionVisitor<R> visitor) {return visitor.visit(this); } 
    public final String name;
}