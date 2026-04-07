package lib.expression;

public class Literal implements Expression { 
    Literal(String value) { this.value = value; } 
    public <R>R accept(ExpressionVisitor<R> visitor) {return visitor.visit(this); } 
    public final String value;

    public Integer asInt() { return Integer.parseInt(value); }
}