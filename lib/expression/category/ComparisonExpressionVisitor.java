package lib.expression.category;

import lib.expression.*;

public interface ComparisonExpressionVisitor<R> {
    R visit(Equality e);
    R visit(Inequality e);
    R visit(LessThan e);
    R visit(GreaterThan e);
    R visit(LessThanOrEqual e);
    R visit(GreaterThanOrEqual e);
}
