package lib.expression.category;

import lib.expression.*;

public interface ComparisonExpressionVisitor<R, E> {
    R visit(Equality<E> e);
    R visit(Inequality<E> e);
    R visit(LessThan<E> e);
    R visit(GreaterThan<E> e);
    R visit(LessThanOrEqual<E> e);
    R visit(GreaterThanOrEqual<E> e);
}
