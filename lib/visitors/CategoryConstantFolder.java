package lib.visitors;

import java.util.function.BiFunction;
import java.util.function.Function;

import lib.expression.*;
import lib.utils.Either;
import lib.utils.EitherVisitor;
import port.IExpressionFactory;

public class CategoryConstantFolder<E> implements ExpressionVisitor<E, E> {
    private final IExpressionFactory<E> factory;
    private final E e;
    private final Function<E, Either<Literal<E>, E>> isLiteral;

    CategoryConstantFolder(IExpressionFactory<E> factory, E e, Function<E, Either<Literal<E>, E>> isLiteral) {
        this.factory = factory;
        this.e = e;
        this.isLiteral = isLiteral;
    }

    private E whenBoth(E left, E right, E otherwise,
            BiFunction<Literal<E>, Literal<E>, String> whenBoth) {
        return whenLiteral(left, otherwise, leftLiteral -> whenLiteral(right, otherwise,
                rightLiteral -> factory.literal(whenBoth.apply(leftLiteral, rightLiteral))));
    }

    private E whenLiteral(E expression, E otherwise,
            Function<Literal<E>, E> whenLiteral) {
        return isLiteral.apply(expression).accept(new EitherVisitor<Literal<E>, E, E>() {
            public E left(Literal<E> literal) {
                return whenLiteral.apply(literal);
            }

            public E right(E e) {
                return otherwise;
            }
        });
    }

    public E visit(VariableReference<E> _e) {
        return e;
    }

    public E visit(Literal<E> _e) {
        return e;
    }

    public E visit(FunctionCall<E> _e) {
        return e;
    }

    public E visit(Addition<E> e) {
        return whenBoth(e.left, e.right, factory.addition(e.left, e.right),
                (left, right) -> Integer.toString(left.asInt() + right.asInt()));
    };

    public E visit(Subtraction<E> e) {
        return whenBoth(e.left, e.right, factory.subtraction(e.left, e.right),
                (left, right) -> Integer.toString(left.asInt() - right.asInt()));
    };

    public E visit(Multiplication<E> e) {
        return whenBoth(e.left, e.right, factory.multiplication(e.left, e.right),
                (left, right) -> Integer.toString(left.asInt() * right.asInt()));
    };

    public E visit(Division<E> e) {
        return whenBoth(e.dividend, e.divisor, factory.division(e.dividend, e.divisor),
                (left, right) -> Integer.toString(left.asInt() / right.asInt()));
    };

    public E visit(Modulo<E> e) {
        return whenBoth(e.left, e.right, factory.modulo(e.left, e.right),
                (left, right) -> Integer.toString(left.asInt() % right.asInt()));
    };

    public E visit(Exponentiation<E> e) {
        return whenBoth(e.base, e.exponent, factory.exponentiation(e.base, e.exponent), (base, exponent) -> Integer
                .toString((int) Math.pow(base.asInt(), exponent.asInt())));
    };

    public E visit(Negation<E> e) {
        return whenLiteral(e.operand, factory.negation(e.operand),
                literal -> factory.literal(Integer.toString(-literal.asInt())));
    };

    public E visit(Equality<E> e) {
        return whenBoth(e.left, e.right, factory.equality(e.left, e.right),
                (left, right) -> left.asInt().intValue() == right.asInt().intValue() ? "1"
                        : "0");
    };

    public E visit(Inequality<E> e) {
        return whenBoth(e.left, e.right, factory.inequality(e.left, e.right),
                (left, right) -> left.asInt().intValue() != right.asInt().intValue() ? "1"
                        : "0");
    };

    public E visit(LessThan<E> e) {
        return whenBoth(e.left, e.right, factory.lessThan(e.left, e.right),
                (left, right) -> left.asInt().intValue() < right.asInt().intValue() ? "1"
                        : "0");
    };

    public E visit(GreaterThan<E> e) {
        return whenBoth(e.left, e.right, factory.greaterThan(e.left, e.right),
                (left, right) -> left.asInt().intValue() > right.asInt().intValue() ? "1"
                        : "0");
    };

    public E visit(LessThanOrEqual<E> e) {
        return whenBoth(e.left, e.right, factory.lessThanOrEqual(e.left, e.right),
                (left, right) -> left.asInt().intValue() <= right.asInt().intValue() ? "1"
                        : "0");
    };

    public E visit(GreaterThanOrEqual<E> e) {
        return whenBoth(e.left, e.right, factory.greaterThanOrEqual(e.left, e.right),
                (left, right) -> left.asInt().intValue() >= right.asInt().intValue() ? "1"
                        : "0");
    };

    public E visit(Conditional<E> c) {
        return whenLiteral(c.condition, factory.conditional(c.condition, c.whenTrue, c.whenFalse),
                literal -> literal.asInt() != 0 ? c.whenTrue : c.whenFalse);
    };

    public E visit(LogicalNot<E> e) {
        return whenLiteral(e.operand, factory.logicalNot(e.operand),
                literal -> factory.literal(literal.asInt() == 0 ? "1" : "0"));
    };

    public E visit(Conjunction<E> e) {
        return whenLiteral(e.left, factory.conjunction(e.left, e.right),
                left -> left.asInt() == 0 ? factory.literal("0") : e.right);
    };

    public E visit(Disjunction<E> e) {
        return whenLiteral(e.left, factory.disjunction(e.left, e.right),
                left -> left.asInt() != 0 ? factory.literal("1") : e.right);
    };
}
