package visitor.expression;

public interface Expression {
    <R>R accept(Visitor<R> visitor);
}

