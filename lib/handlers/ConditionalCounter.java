package lib.handlers;

import lib.expression.*;

public class ConditionalCounter implements Visitor<Integer> {
    ConditionalCounter() {}

    public int handle(Expression expression) {
        return count(expression);
    }
    private int count(Expression expression) {
        int result = expression.accept(this);
        return result;
    }

    public Integer visit(Literal expression) { return 0; }
    public Integer visit(VariableReference expression) { return 0; }
    public Integer visit(Addition expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(Subtraction expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(Multiplication expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(Division expression) { return count(expression.dividend) + count(expression.divisor); }
    public Integer visit(Negation expression) { return count(expression.operand); }
    public Integer visit(Modulo expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(Exponentiation expression) { return count(expression.base) + count(expression.exponent); }
    public Integer visit(Equality expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(Inequality expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(LessThan expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(GreaterThan expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(LessThanOrEqual expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(GreaterThanOrEqual expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(Conjunction expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(Disjunction expression) { return count(expression.left) + count(expression.right); }
    public Integer visit(LogicalNot expression) { return count(expression.operand); }
    public Integer visit(Conditional expression) { return 1 + count(expression.condition) + count(expression.whenTrue) + count(expression.whenFalse); }
    public Integer visit(FunctionCall expression) {
        int total = count(expression.callee);
        for (var argument : expression.arguments) {
            total += count(argument);
        }
        return total;
    }

}