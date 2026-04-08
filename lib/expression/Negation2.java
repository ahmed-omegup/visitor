package lib.expression;

public class Negation2 implements ExpressionV2 {
    Negation2(ExpressionV2 operand) { this.operand = operand; }
    public <R>R accept(ExpressionVisitor2<R> visitor) {return visitor.visit(this); }
    public final ExpressionV2 operand;
}