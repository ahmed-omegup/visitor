package lib.expression.category;

import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.LogicalNot;

public interface LogicalExpressionVisitor<R, E> {
    R visit(Conjunction<E> e);
    R visit(Disjunction<E> e);
    R visit(LogicalNot<E> e);
    R visit(Conditional<E> e);
}
