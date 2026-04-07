package lib.expression.category;

import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.LogicalNot;

public interface LogicalExpressionVisitor<R> {
    R visit(Conjunction e);
    R visit(Disjunction e);
    R visit(LogicalNot e);
    R visit(Conditional e);
}
