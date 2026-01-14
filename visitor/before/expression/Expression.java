package visitor.before.expression;

public interface Expression {
    <R>R accept(Visitor<R> visitor);
}

