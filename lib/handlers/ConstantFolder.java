package lib.handlers;

import lib.expression.*;
import port.IFactory;

public class ConstantFolder implements Visitor<Expression> {
    private final IFactory factory;
    private final LiteralValueExtractor literalValueExtractor = new LiteralValueExtractor();

    ConstantFolder(IFactory factory) {
        this.factory = factory;
    }

    public Expression handle(Expression expression) {
        return fold(expression);
    }

    private Expression fold(Expression expression) {
        return expression.accept(this);
    }

    public Expression visit(Literal expression) {
        return expression;
    }

    public Expression visit(VariableReference expression) {
        return expression;
    }

    public Expression visit(Addition expression) {
        return foldBinary(fold(expression.left), fold(expression.right), factory::addition, (left, right) -> left + right);
    }

    public Expression visit(Subtraction expression) {
        return foldBinary(fold(expression.left), fold(expression.right), factory::subtraction, (left, right) -> left - right);
    }

    public Expression visit(Multiplication expression) {
        return foldBinary(fold(expression.left), fold(expression.right), factory::multiplication, (left, right) -> left * right);
    }

    public Expression visit(Division expression) {
        return foldBinary(fold(expression.dividend), fold(expression.divisor), factory::division, (left, right) -> left / right);
    }

    public Expression visit(Negation expression) {
        var operand = fold(expression.operand);
        var value = literalValueExtractor.handle(operand);
        return value == null ? factory.negation(operand) : factory.literal(Integer.toString(-value));
    }

    public Expression visit(Modulo expression) {
        return foldBinary(fold(expression.left), fold(expression.right), factory::modulo, (left, right) -> left % right);
    }

    public Expression visit(Exponentiation expression) {
        return foldBinary(fold(expression.base), fold(expression.exponent), factory::exponentiation, (left, right) -> (int) Math.pow(left, right));
    }

    public Expression visit(Equality expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::equality, (left, right) -> left.intValue() == right.intValue());
    }

    public Expression visit(Inequality expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::inequality, (left, right) -> left.intValue() != right.intValue());
    }

    public Expression visit(LessThan expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::lessThan, (left, right) -> left.intValue() < right.intValue());
    }

    public Expression visit(GreaterThan expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::greaterThan, (left, right) -> left.intValue() > right.intValue());
    }

    public Expression visit(LessThanOrEqual expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::lessThanOrEqual, (left, right) -> left.intValue() <= right.intValue());
    }

    public Expression visit(GreaterThanOrEqual expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::greaterThanOrEqual, (left, right) -> left.intValue() >= right.intValue());
    }

    public Expression visit(Conjunction expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::conjunction, (left, right) -> left != 0 && right != 0);
    }

    public Expression visit(Disjunction expression) {
        return foldComparison(fold(expression.left), fold(expression.right), factory::disjunction, (left, right) -> left != 0 || right != 0);
    }

    public Expression visit(LogicalNot expression) {
        var operand = fold(expression.operand);
        var value = literalValueExtractor.handle(operand);
        return value == null ? factory.logicalNot(operand) : factory.literal(value == 0 ? "1" : "0");
    }

    public Expression visit(Conditional expression) {
        var condition = fold(expression.condition);
        var whenTrue = fold(expression.whenTrue);
        var whenFalse = fold(expression.whenFalse);
        var value = literalValueExtractor.handle(condition);
        if (value == null) {
            return factory.conditional(condition, whenTrue, whenFalse);
        }
        return value != 0 ? whenTrue : whenFalse;
    }

    public Expression visit(FunctionCall expression) {
        var callee = fold(expression.callee);
        var arguments = new Expression[expression.arguments.length];
        for (int index = 0; index < expression.arguments.length; index++) {
            arguments[index] = fold(expression.arguments[index]);
        }
        return factory.functionCall(callee, arguments);
    }

    private Expression foldBinary(Expression left, Expression right, BinaryFactory binaryFactory, BinaryOperation operation) {
        var leftValue = literalValueExtractor.handle(left);
        var rightValue = literalValueExtractor.handle(right);
        if (leftValue != null && rightValue != null) {
            return factory.literal(Integer.toString(operation.apply(leftValue, rightValue)));
        }
        return binaryFactory.create(left, right);
    }

    private Expression foldComparison(Expression left, Expression right, BinaryFactory binaryFactory, ComparisonOperation operation) {
        var leftValue = literalValueExtractor.handle(left);
        var rightValue = literalValueExtractor.handle(right);
        if (leftValue != null && rightValue != null) {
            return factory.literal(operation.apply(leftValue, rightValue) ? "1" : "0");
        }
        return binaryFactory.create(left, right);
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