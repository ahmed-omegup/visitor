package lib.visitors;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.*;
import lib.expression.category.*;
import port.IExpressionFactory;

public class ConstantFolder implements Function<Expression, Expression> {
    private final IExpressionFactory<Expression> factory;
    private final CategoryVisitor cat = new CategoryVisitor();

    public ConstantFolder(IExpressionFactory<Expression> factory) {
        this.factory = factory;
    }

    private Expression foldOnce(Expression expression) {
        return expression.accept(cat).accept(new CategoryConstantFolder(factory, expression));
    }

    @Override
    public Expression apply(Expression expression) {
        ExpressionMapper mapper = new ExpressionMapper(factory, (_, recurse) -> foldOnce(recurse.get()));
        return mapper.apply(expression);
    }

}


class CategoryConstantFolder implements CategoryExpressionVisitor<Expression> {
    private final IExpressionFactory<Expression> factory;
    private final Expression e;

    CategoryConstantFolder(IExpressionFactory<Expression> factory, Expression e) {
        this.factory = factory;
        this.e = e;
    }

    private Expression whenBoth(Expression left, Expression right, Expression otherwise,
            BiFunction<Literal, Literal, String> whenBoth) {
        return whenLiteral(left, otherwise, leftLiteral -> whenLiteral(right, otherwise,
                rightLiteral -> factory.literal(whenBoth.apply(leftLiteral, rightLiteral))));
    }

    private Expression whenLiteral(Expression expression, Expression otherwise,
            Function<Literal, Expression> whenLiteral) {
        return expression.accept(new FallbackVisitor<>(_e -> otherwise) {
            public Expression visit(Literal literal) {
                return whenLiteral.apply(literal);
            }
        });
    }

    @Override
    public Expression visit(LeafExpression _e) {
        return e;
    }

    public Expression visit(FunctionCall _e) {
        return e;
    }

    public Expression visit(ArithmeticExpression b) {
        return b.accept(new ArithmeticExpressionVisitor<Expression>() {
            public Expression visit(Addition e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> Integer.toString(left.asInt() + right.asInt()));
            };

            public Expression visit(Subtraction e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> Integer.toString(left.asInt() - right.asInt()));
            };

            public Expression visit(Multiplication e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> Integer.toString(left.asInt() * right.asInt()));
            };

            public Expression visit(Division e) {
                return whenBoth(e.dividend, e.divisor, e,
                        (left, right) -> Integer.toString(left.asInt() / right.asInt()));
            };

            public Expression visit(Modulo e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> Integer.toString(left.asInt() % right.asInt()));
            };

            public Expression visit(Exponentiation e) {
                return whenBoth(e.base, e.exponent, e, (base, exponent) -> Integer
                        .toString((int) Math.pow(base.asInt(), exponent.asInt())));
            };

            public Expression visit(Negation e) {
                return whenLiteral(e.operand, e,
                        literal -> factory.literal(Integer.toString(-literal.asInt())));
            };

        });
    };

    public Expression visit(ComparisonExpression b) {
        return b.accept(new ComparisonExpressionVisitor<Expression>() {
            public Expression visit(Equality e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> left.asInt().intValue() == right.asInt().intValue() ? "1"
                                : "0");
            };

            public Expression visit(Inequality e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> left.asInt().intValue() != right.asInt().intValue() ? "1"
                                : "0");
            };

            public Expression visit(LessThan e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> left.asInt().intValue() < right.asInt().intValue() ? "1"
                                : "0");
            };

            public Expression visit(GreaterThan e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> left.asInt().intValue() > right.asInt().intValue() ? "1"
                                : "0");
            };

            public Expression visit(LessThanOrEqual e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> left.asInt().intValue() <= right.asInt().intValue() ? "1"
                                : "0");
            };

            public Expression visit(GreaterThanOrEqual e) {
                return whenBoth(e.left, e.right, e,
                        (left, right) -> left.asInt().intValue() >= right.asInt().intValue() ? "1"
                                : "0");
            };
        });
    };

    public Expression visit(LogicalExpression b) {
        return b.accept(new LogicalExpressionVisitor<Expression>() {
            public Expression visit(Conditional c) {
                return whenLiteral(c.condition, c,
                        literal -> literal.asInt() != 0 ? c.whenTrue : c.whenFalse);
            };

            public Expression visit(LogicalNot e) {
                return whenLiteral(e.operand, e,
                        literal -> factory.literal(literal.asInt() == 0 ? "1" : "0"));
            };

            public Expression visit(Conjunction e) {
                return whenLiteral(e.left, e,
                        left -> left.asInt() == 0 ? factory.literal("0") : e.right);
            };

            public Expression visit(Disjunction e) {
                return whenLiteral(e.left, e,
                        left -> left.asInt() != 0 ? factory.literal("1") : e.right);
            };
        });
    };
}
