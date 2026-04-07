package lib.expression.category;

import lib.expression.Literal;
import lib.expression.VariableReference;

public interface LeafExpressionVisitor<R> {
    R visit(VariableReference e);
    R visit(Literal e);
}
