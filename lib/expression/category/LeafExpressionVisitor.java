package lib.expression.category;

import lib.expression.Literal;
import lib.expression.VariableReference;

public interface LeafExpressionVisitor<R, E> {
    R visit(VariableReference<E> e);
    R visit(Literal<E> e);
}
