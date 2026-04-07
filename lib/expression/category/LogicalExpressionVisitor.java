package lib.expression.category;

import lib.expression.Conjunction;
import lib.expression.Disjunction;

public interface LogicalExpressionVisitor<R> {
    R visit(Conjunction e);
    R visit(Disjunction e);
}
