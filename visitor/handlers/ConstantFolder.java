package visitor.handlers;

import visitor.expression.Addition;
import visitor.expression.Conditional;
import visitor.expression.Conjunction;
import visitor.expression.Disjunction;
import visitor.expression.Division;
import visitor.expression.Equality;
import visitor.expression.Exponentiation;
import visitor.expression.Expression;
import visitor.expression.FunctionCall;
import visitor.expression.GreaterThan;
import visitor.expression.GreaterThanOrEqual;
import visitor.expression.Inequality;
import visitor.expression.LessThan;
import visitor.expression.LessThanOrEqual;
import visitor.expression.Literal;
import visitor.expression.LogicalNot;
import visitor.expression.Modulo;
import visitor.expression.Multiplication;
import visitor.expression.Negation;
import visitor.expression.Subtraction;
import visitor.expression.VariableReference;
import visitor.expression.Visitor;

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
                return foldBinary(fold(expression.left), fold(expression.right), Addition::new, (left, right) -> left + right);
            }

            public Expression visit(Subtraction expression) {
                return foldBinary(fold(expression.left), fold(expression.right), Subtraction::new, (left, right) -> left - right);
            }

            public Expression visit(Multiplication expression) {
                return foldBinary(fold(expression.left), fold(expression.right), Multiplication::new, (left, right) -> left * right);
            }

            public Expression visit(Division expression) {
                return foldBinary(fold(expression.dividend), fold(expression.divisor), Division::new, (left, right) -> left / right);
            }

            public Expression visit(Negation expression) {
                var operand = fold(expression.operand);
                var value = literalValueExtractor.handle(operand);
                return value == null ? new Negation(operand) : new Literal(Integer.toString(-value));
            }

            public Expression visit(Modulo expression) {
                return foldBinary(fold(expression.left), fold(expression.right), Modulo::new, (left, right) -> left % right);
            }

            public Expression visit(Exponentiation expression) {
                return foldBinary(fold(expression.base), fold(expression.exponent), Exponentiation::new, (left, right) -> (int) Math.pow(left, right));
            }

            public Expression visit(Equality expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Equality::new, (left, right) -> left.intValue() == right.intValue());
            }

            public Expression visit(Inequality expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Inequality::new, (left, right) -> left.intValue() != right.intValue());
            }

            public Expression visit(LessThan expression) {
                return foldComparison(fold(expression.left), fold(expression.right), LessThan::new, (left, right) -> left.intValue() < right.intValue());
            }

            public Expression visit(GreaterThan expression) {
                return foldComparison(fold(expression.left), fold(expression.right), GreaterThan::new, (left, right) -> left.intValue() > right.intValue());
            }

            public Expression visit(LessThanOrEqual expression) {
                return foldComparison(fold(expression.left), fold(expression.right), LessThanOrEqual::new, (left, right) -> left.intValue() <= right.intValue());
            }

            public Expression visit(GreaterThanOrEqual expression) {
                return foldComparison(fold(expression.left), fold(expression.right), GreaterThanOrEqual::new, (left, right) -> left.intValue() >= right.intValue());
            }

            public Expression visit(Conjunction expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Conjunction::new, (left, right) -> left != 0 && right != 0);
            }

            public Expression visit(Disjunction expression) {
                return foldComparison(fold(expression.left), fold(expression.right), Disjunction::new, (left, right) -> left != 0 || right != 0);
            }

            public Expression visit(LogicalNot expression) {
                var operand = fold(expression.operand);
                var value = literalValueExtractor.handle(operand);
                return value == null ? new LogicalNot(operand) : new Literal(value == 0 ? "1" : "0");
            }

            public Expression visit(Conditional expression) {
                var condition = fold(expression.condition);
                var whenTrue = fold(expression.whenTrue);
                var whenFalse = fold(expression.whenFalse);
                var value = literalValueExtractor.handle(condition);
                if (value == null) {
                    return new Conditional(condition, whenTrue, whenFalse);
                }
                return value != 0 ? whenTrue : whenFalse;
            }

            public Expression visit(FunctionCall expression) {
                var callee = fold(expression.callee);
                var arguments = new Expression[expression.arguments.length];
                for (int index = 0; index < expression.arguments.length; index++) {
                    arguments[index] = fold(expression.arguments[index]);
                }
                return new FunctionCall(callee, arguments);
            }
        });
    }

    private Expression foldBinary(Expression left, Expression right, BinaryFactory factory, BinaryOperation operation) {
        var leftValue = literalValueExtractor.handle(left);
        var rightValue = literalValueExtractor.handle(right);
        if (leftValue != null && rightValue != null) {
            return new Literal(Integer.toString(operation.apply(leftValue, rightValue)));
        }
        return factory.create(left, right);
    }

    private Expression foldComparison(Expression left, Expression right, BinaryFactory factory, ComparisonOperation operation) {
        var leftValue = literalValueExtractor.handle(left);
        var rightValue = literalValueExtractor.handle(right);
        if (leftValue != null && rightValue != null) {
            return new Literal(operation.apply(leftValue, rightValue) ? "1" : "0");
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