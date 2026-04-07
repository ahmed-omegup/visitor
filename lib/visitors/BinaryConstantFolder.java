package lib.visitors;

import lib.expression.*;
import lib.expression.category.*;
import port.IExpressionFactory;

class BinaryConstantFolder extends ExpressionLiteral implements BinaryExpressionVisitor<Expression> {
    private final Expression e;

    BinaryConstantFolder(IExpressionFactory<Expression> factory, Expression e) {
        super(factory);
        this.e = e;
    }

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
