package lib.expression.category;

import lib.expression.*;

public interface ArithmeticExpressionVisitor<R, E> {
    R visit(Addition<E> e);
    R visit(Subtraction<E> e);
    R visit(Multiplication<E> e);
    R visit(Division<E> e);
    R visit(Modulo<E> e);
    R visit(Exponentiation<E> e);
    R visit(Negation<E> e);
}
