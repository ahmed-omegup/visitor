package lib.expression.category;

import lib.expression.*;

public interface ArithmeticExpressionVisitor<R> {
    R visit(Addition e);
    R visit(Subtraction e);
    R visit(Multiplication e);
    R visit(Division e);
    R visit(Modulo e);
    R visit(Exponentiation e);
    R visit(Negation e);
}
