package lib.legacy;

import lib.expression.*;

public class SideEffectFreeChecker extends AbstractExpressionFunction<Boolean> {
    SideEffectFreeChecker() {}

    public Boolean apply(Expression expression) {
        return check(expression);
    }
    private Boolean check(Expression expression) {
        Boolean result = visitExpression(expression);
        return result;
    }

    public Boolean visit(Literal expression) { return true; }
    public Boolean visit(VariableReference expression) { return true; }
    public Boolean visit(Addition expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(Subtraction expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(Multiplication expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(Division expression) { return check(expression.dividend) && check(expression.divisor); }
    public Boolean visit(Negation expression) { return check(expression.operand); }
    public Boolean visit(Modulo expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(Exponentiation expression) { return check(expression.base) && check(expression.exponent); }
    public Boolean visit(Equality expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(Inequality expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(LessThan expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(GreaterThan expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(LessThanOrEqual expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(GreaterThanOrEqual expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(Conjunction expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(Disjunction expression) { return check(expression.left) && check(expression.right); }
    public Boolean visit(LogicalNot expression) { return check(expression.operand); }
    public Boolean visit(Conditional expression) { return check(expression.condition) && check(expression.whenTrue) && check(expression.whenFalse); }
    public Boolean visit(FunctionCall expression) { return false; }

}