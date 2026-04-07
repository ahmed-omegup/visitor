package lib.expression.category;

import lib.expression.*;

public interface BinaryExpression {
    <R>R accept(BinaryExpressionVisitor<R> visitor);
}
