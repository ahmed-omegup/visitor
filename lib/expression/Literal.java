package lib.expression;

public class Literal implements Expression { 
    Literal(String value) { this.value = value; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final String value;
}