package lib.expression;

public interface ExpressionV2 {
    <R>R accept(ExpressionVisitor2<R> visitor);
}
