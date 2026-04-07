package lib.expression.category;

public interface BinaryExpressionVisitor<R> {
    R visit(BinaryArithmeticExpression e);
    R visit(ComparisonExpression e);
    R visit(LogicalExpression e);
}
