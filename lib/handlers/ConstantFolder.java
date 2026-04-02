package lib.handlers;

import static lib.expression.Factory.*;
import lib.expression.*;

public class ConstantFolder {
    private final LiteralValueExtractor literalValueExtractor = new LiteralValueExtractor();

    public Expression handle(Expression expression) {
        return fold(expression);
    }

    private Expression fold(Expression expression) {
        return expression.accept(new Visitor<Expression>() {
            public Expression visit(Literal expression) {
                return expression;
            }

            public Expression visit(VariableReference expression) {
                return expression;
            }

            public Expression visit(Addition expression) {
                return foldBinary(fold(expression.left), fold(expression.right), Factory::addition, (left, right) -> left + right);
            }

            public Expression visit(Subtraction expression) {
                return foldBinary(fold(expression.left), fold(expression.right), Factory::subtraction, (left, right) -> left - right);
            }

            public Expression visit(Multiplication expression) {
                return foldBinary(fold(expression.left), fold(expression.right), Factory::multiplication, (left, right) -> left * right);
            }

            public Expression visit(Division expression) {
                return foldBinary(fold(expression.dividend), fold(expression.divisor), Factory::division, (left, right) -> left / right);
            }

            public Expression visit(Negation expression) {
                var operand = fold(expression.operand);
                var value = literalValueExtractor.handle(operand);
                return value == null ? negation(operand) : literal(Integer.toString(-value));
            }

            public Expression visit(Modulo expression) {
                return foldBinary(fold(expression.left), fold(expression.right), Factory::modulo, (left, right) -> left % right);
            }

            public Expression visit(Exponentiation expression) {
                return foldBinary(fold(expression.base), fold(expression.exponent), Factory::exponentiation, (left, right) -> (int) Math.pow(left, right));
            }

            public Expression visit(Equality expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::equality, (left, right) -> left.intValue() == right.intValue());
            }

            public Expression visit(Inequality expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::inequality, (left, right) -> left.intValue() != right.intValue());
            }

            public Expression visit(LessThan expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::lessThan, (left, right) -> left.intValue() < right.intValue());
            }

            public Expression visit(GreaterThan expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::greaterThan, (left, right) -> left.intValue() > right.intValue());
            }

            public Expression visit(LessThanOrEqual expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::lessThanOrEqual, (left, right) -> left.intValue() <= right.intValue());
            }

            public Expression visit(GreaterThanOrEqual expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::greaterThanOrEqual, (left, right) -> left.intValue() >= right.intValue());
            }

            public Expression visit(Conjunction expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::conjunction, (left, right) -> left != 0 && right != 0);
            }

            public Expression visit(Disjunction expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Factory::disjunction, (left, right) -> left != 0 || right != 0);
            }

            public Expression visit(LogicalNot expression) {
                var operand = fold(expression.operand);
                var value = literalValueExtractor.handle(operand);
                return value == null ? logicalNot(operand) : literal(value == 0 ? "1" : "0");
            }

            public Expression visit(Conditional expression) {
                var condition = fold(expression.condition);
                var whenTrue = fold(expression.whenTrue);
                var whenFalse = fold(expression.whenFalse);
                var value = literalValueExtractor.handle(condition);
                if (value == null) {
                    return conditional(condition, whenTrue, whenFalse);
                }
                return value != 0 ? whenTrue : whenFalse;
            }

            public Expression visit(FunctionCall expression) {
                var callee = fold(expression.callee);
                var arguments = new Expression[expression.arguments.length];
                for (int index = 0; index < expression.arguments.length; index++) {
                    arguments[index] = fold(expression.arguments[index]);
                }
                return functionCall(callee, arguments);
            }
        });
    }

    private Expression foldBinary(Expression left, Expression right, BinaryFactory factory, BinaryOperation operation) {
        var leftValue = literalValueExtractor.handle(left);
        var rightValue = literalValueExtractor.handle(right);
        if (leftValue != null && rightValue != null) {
            return literal(Integer.toString(operation.apply(leftValue, rightValue)));
        }
        return factory.create(left, right);
    }

    private Expression foldComparison(Expression left, Expression right, BinaryFactory factory, ComparisonOperation operation) {
        var leftValue = literalValueExtractor.handle(left);
        var rightValue = literalValueExtractor.handle(right);
        if (leftValue != null && rightValue != null) {
            return literal(operation.apply(leftValue, rightValue) ? "1" : "0");
        }
        return factory.create(left, right);
    }

    private interface BinaryFactory {
        Expression create(Expression left, Expression right);
    }

    private interface BinaryOperation {
        int apply(int left, int right);
    }

    private interface ComparisonOperation {
        boolean apply(Integer left, Integer right);
    }
}