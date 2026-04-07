package lib.legacy;

import lib.expression.*;
import port.IExpressionFactory;

public class ConstantFolder extends AbstractExpressionFunction<Expression> {
    private final IExpressionFactory<Expression> factory;
    private final LiteralValueExtractor literalValueExtractor = new LiteralValueExtractor();

    ConstantFolder(IExpressionFactory<Expression> factory) {
        this.factory = factory;
    }

    public Expression apply(Expression expression) {
        return fold(expression);
    }

    private Expression fold(Expression expression) {
        return visitExpression(expression);
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
        var value = operand.accept(literalValueExtractor);
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
        var value = operand.accept(literalValueExtractor);
        return value == null ? factory.logicalNot(operand) : factory.literal(value == 0 ? "1" : "0");
    }

    public Expression visit(Conditional expression) {
        var condition = fold(expression.condition);
        var whenTrue = fold(expression.whenTrue);
        var whenFalse = fold(expression.whenFalse);
        var value = condition.accept(literalValueExtractor);
        if (value == null) {
            return factory.conditional(condition, whenTrue, whenFalse);
        }
        return value != 0 ? whenTrue : whenFalse;
    }

    public Expression visit(FunctionCall expression) {
        var callee = fold(expression.callee);
        var arguments = expression.arguments.stream().map(this::fold).toList();
        return factory.functionCall(callee, arguments);
    }

    private Expression foldBinary(Expression left, Expression right, BinaryFactory binaryFactory, BinaryOperation operation) {
        var leftValue = left.accept(literalValueExtractor);
        var rightValue = right.accept(literalValueExtractor);
        if (leftValue != null && rightValue != null) {
            return factory.literal(Integer.toString(operation.apply(leftValue, rightValue)));
        }
        return binaryFactory.create(left, right);
    }

    private Expression foldComparison(Expression left, Expression right, BinaryFactory binaryFactory, ComparisonOperation operation) {
        var leftValue = left.accept(literalValueExtractor);
        var rightValue = right.accept(literalValueExtractor);
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