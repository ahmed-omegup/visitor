package lib.visitors;

import lib.expression.*;

public class BinaryOperatorCounter extends AbstractExpressionFunction<Integer> {
    BinaryOperatorCounter() {}

    public Integer apply(Expression expression) {
        return count(expression);
    }
    private Integer count(Expression expression) {
        Integer result = visitExpression(expression);
        return result;
    }

    private int binary(Expression left, Expression right) {
        return 1 + count(left) + count(right);
    }

    public Integer visit(Literal expression) { return 0; }
    public Integer visit(VariableReference expression) { return 0; }
    public Integer visit(Addition expression) { return binary(expression.left, expression.right); }
    public Integer visit(Subtraction expression) { return binary(expression.left, expression.right); }
    public Integer visit(Multiplication expression) { return binary(expression.left, expression.right); }
    public Integer visit(Division expression) { return binary(expression.dividend, expression.divisor); }
    public Integer visit(Negation expression) { return count(expression.operand); }
    public Integer visit(Modulo expression) { return binary(expression.left, expression.right); }
    public Integer visit(Exponentiation expression) { return binary(expression.base, expression.exponent); }
    public Integer visit(Equality expression) { return binary(expression.left, expression.right); }
    public Integer visit(Inequality expression) { return binary(expression.left, expression.right); }
    public Integer visit(LessThan expression) { return binary(expression.left, expression.right); }
    public Integer visit(GreaterThan expression) { return binary(expression.left, expression.right); }
    public Integer visit(LessThanOrEqual expression) { return binary(expression.left, expression.right); }
    public Integer visit(GreaterThanOrEqual expression) { return binary(expression.left, expression.right); }
    public Integer visit(Conjunction expression) { return binary(expression.left, expression.right); }
    public Integer visit(Disjunction expression) { return binary(expression.left, expression.right); }
    public Integer visit(LogicalNot expression) { return count(expression.operand); }
    public Integer visit(Conditional expression) { return count(expression.condition) + count(expression.whenTrue) + count(expression.whenFalse); }
    public Integer visit(FunctionCall expression) { int total = count(expression.callee); for (var argument : expression.arguments) { total += count(argument); } return total; }

}