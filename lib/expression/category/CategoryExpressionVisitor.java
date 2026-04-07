package lib.expression.category;

import lib.expression.FunctionCall;

public interface CategoryExpressionVisitor<R, E> {
    R visit(LeafExpression<E> e);
    R visit(ArithmeticExpression<E> e);
    R visit(ComparisonExpression<E> e);
    R visit(LogicalExpression<E> e);
    R visit(FunctionCall<E> e);
}
