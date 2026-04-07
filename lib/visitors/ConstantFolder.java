package lib.visitors;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.*;
import lib.expression.category.*;
import port.IExpressionFactory;

public class ConstantFolder implements Function<Expression, Expression> {
    private final IExpressionFactory<Expression> factory;
    private final Function<Expression, Expression> folderOnce;

    ConstantFolder(IExpressionFactory<Expression> factory) {
        this.factory = factory;
        this.folderOnce = new ExpressionFolderOnce(factory);
    }

    @Override
    public Expression apply(Expression expression) {
        ExpressionMapper mapper = new ExpressionMapper(factory, (_, recurse) -> folderOnce.apply(recurse.get()));
        return mapper.apply(expression);
    }

}

class LiteralVisitor extends FallbackVisitor<Expression> {
    private final Function<Literal, Expression> handleLiteral;

    LiteralVisitor(Expression otherwise, Function<Literal, Expression> handleLiteral) {
        super(_e -> otherwise);
        this.handleLiteral = handleLiteral;
    }

    @Override
    public Expression visit(Literal literal) {
        return handleLiteral.apply(literal);
    }
}

class ExpressionFolderOnce implements Function<Expression, Expression> {
    private final CategoryVisitor cat = new CategoryVisitor();
    private final IExpressionFactory<Expression> factory;

    private Expression whenBoth(Expression left, Expression right, Expression otherwise,
            BiFunction<Literal, Literal, String> whenBoth) {
        return left.accept(new LiteralVisitor(otherwise, leftLiteral -> right.accept(new LiteralVisitor(otherwise,
                rightLiteral -> factory.literal(whenBoth.apply(leftLiteral, rightLiteral))))));
    }

    private Expression whenLiteral(Expression expression, Expression otherwise,
            Function<Literal, Expression> whenLiteral) {
        return expression.accept(new LiteralVisitor(otherwise, whenLiteral));
    }

    ExpressionFolderOnce(IExpressionFactory<Expression> factory) {
        this.factory = factory;
    }

    public Expression apply(Expression e) {
        return e.accept(cat).accept(new CategoryExpressionVisitor<Expression>() {
            @Override
            public Expression visit(LeafExpression _e) {
                return e;
            }

            @Override
            public Expression visit(OddExpression ex) {
                return ex.accept(new OddExpressionVisitor<Expression>() {
                    public Expression visit(Conditional c) {
                        return whenLiteral(c.condition, c,
                                literal -> literal.asInt() != 0 ? c.whenTrue : c.whenFalse);
                    };

                    public Expression visit(LogicalNot e) {
                        return whenLiteral(e.operand, e,
                                literal -> factory.literal(literal.asInt() == 0 ? "1" : "0"));
                    };

                    public Expression visit(Negation e) {
                        return whenLiteral(e.operand, e,
                                literal -> factory.literal(Integer.toString(-literal.asInt())));
                    };
                });
            }

            @Override
            public Expression visit(BinaryExpression b) {
                return b.accept(new BinaryExpressionVisitor<Expression>() {
                    public Expression visit(BinaryArithmeticExpression b) {
                        return b.accept(new BinaryArithmeticExpressionVisitor<Expression>() {
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
                            public Expression visit(Conjunction e) {
                                return whenBoth(e.left, e.right, e,
                                        (left, right) -> left.asInt() != 0 && right.asInt() != 0 ? "1" : "0");
                            };

                            public Expression visit(Disjunction e) {
                                return whenBoth(e.left, e.right, e,
                                        (left, right) -> left.asInt() != 0 || right.asInt() != 0 ? "1" : "0");
                            };
                        });
                    };
                });
            }

            @Override
            public Expression visit(FunctionCall _e) {
                return e;
            }

        });
    };

}