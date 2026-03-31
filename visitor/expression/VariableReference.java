package visitor.expression;

public class VariableReference implements Expression { 
    public VariableReference(String name) { this.name = name; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final String name;
}