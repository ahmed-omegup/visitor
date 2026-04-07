package lib.expression;

public class VariableReference<E> implements Expression<E> {
    VariableReference(String name) { this.name = name; }
    public <R>R accept(EExpressionVisitor<R, E> visitor) {return visitor.visit(this); }
    public final String name;
}