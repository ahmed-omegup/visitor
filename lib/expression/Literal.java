package lib.expression;

import java.util.function.Function;

public class Literal<E> implements EExpression<E> {
    Literal(String value) { this.value = value; }
    public <R>R accept(EExpressionVisitor<R, E> visitor) {return visitor.visit(this); }
    public final String value;
}