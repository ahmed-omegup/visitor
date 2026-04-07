package lib.expression.category;

import lib.expression.Conditional;
import lib.expression.LogicalNot;
import lib.expression.Negation;

public interface OddExpressionVisitor<R> {
    R visit(Negation e);
    R visit(LogicalNot e);
    R visit(Conditional e);
}
