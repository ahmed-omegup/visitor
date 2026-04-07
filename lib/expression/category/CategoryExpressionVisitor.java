package lib.expression.category;

import lib.expression.FunctionCall;

public interface CategoryExpressionVisitor<R> {
    R visit(LeafExpression e);
    R visit(OddExpression e);
    R visit(BinaryExpression e);
    R visit(FunctionCall e);
}
