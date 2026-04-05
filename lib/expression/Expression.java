package lib.expression;

public interface Expression {
    <R>R accept(Visitor1<R> visitor);
}

