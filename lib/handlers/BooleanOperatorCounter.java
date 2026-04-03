package lib.handlers;

import lib.expression.*;

public class BooleanOperatorCounter implements Visitor<Integer> {
    BooleanOperatorCounter() {}

    public Integer handle(Expression expression) {
        return count(expression);
    }
    private Integer count(Expression expression) {
        Integer result = expression.accept(this);
        return result;
    }

    private int branch(Expression left, Expression right) {
        return count(left) + count(right);
    }

    public Integer visit(Literal expression) { return 0; }
    public Integer visit(VariableReference expression) { return 0; }
    public Integer visit(Addition expression) { return branch(expression.left, expression.right); }
    public Integer visit(Subtraction expression) { return branch(expression.left, expression.right); }
    public Integer visit(Multiplication expression) { return branch(expression.left, expression.right); }
    public Integer visit(Division expression) { return branch(expression.dividend, expression.divisor); }
    public Integer visit(Negation expression) { return count(expression.operand); }
    public Integer visit(Modulo expression) { return branch(expression.left, expression.right); }
    public Integer visit(Exponentiation expression) { return branch(expression.base, expression.exponent); }
    public Integer visit(Equality expression) { return branch(expression.left, expression.right); }
    public Integer visit(Inequality expression) { return branch(expression.left, expression.right); }
    public Integer visit(LessThan expression) { return branch(expression.left, expression.right); }
    public Integer visit(GreaterThan expression) { return branch(expression.left, expression.right); }
    public Integer visit(LessThanOrEqual expression) { return branch(expression.left, expression.right); }
    public Integer visit(GreaterThanOrEqual expression) { return branch(expression.left, expression.right); }
    public Integer visit(Conjunction expression) { return 1 + branch(expression.left, expression.right); }
    public Integer visit(Disjunction expression) { return 1 + branch(expression.left, expression.right); }
    public Integer visit(LogicalNot expression) { return 1 + count(expression.operand); }
    public Integer visit(Conditional expression) { return count(expression.condition) + count(expression.whenTrue) + count(expression.whenFalse); }
    public Integer visit(FunctionCall expression) { int total = count(expression.callee); for (var argument : expression.arguments) { total += count(argument); } return total; }

}