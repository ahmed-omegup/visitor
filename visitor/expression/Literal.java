package visitor.expression;

public class Literal implements Expression { 
    public Literal(String value) { this.value = value; } 
    public <R>R accept(Visitor<R> visitor) {return visitor.visit(this); } 
    public final String value;
}