package lib.expression.category;

import lib.expression.FunctionCall;

public interface CategoryExpressionVisitor<R> {
    R visit(LeafExpression e);
    R visit(ArithmeticExpression e);
    R visit(ComparisonExpression e);
    R visit(LogicalExpression e);
    R visit(FunctionCall e);
}
