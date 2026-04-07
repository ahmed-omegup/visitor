package lib.legacy;

import lib.expression.*;

public class ZeroDivisionRiskDetector extends AbstractExpressionFunction<Boolean> {
    ZeroDivisionRiskDetector() {}

    private boolean checkingLiteralZero;

    public Boolean apply(Expression expression) {
        return detect(expression);
    }

    private Boolean detect(Expression expression) {
        return visitExpression(expression);
    }

    private boolean literalZero(Expression expression) {
        boolean previousCheckingLiteralZero = this.checkingLiteralZero;
        this.checkingLiteralZero = true;
        boolean result = visitExpression(expression);
        this.checkingLiteralZero = previousCheckingLiteralZero;
        return result;
    }

    private boolean literalProbeMiss() {
        return checkingLiteralZero;
    }

    public Boolean visit(Literal expression) { return checkingLiteralZero && "0".equals(expression.value); }
    public Boolean visit(VariableReference expression) { return false; }
    public Boolean visit(Addition expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(Subtraction expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(Multiplication expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(Division expression) { return literalProbeMiss() ? false : literalZero(expression.divisor) || detect(expression.dividend) || detect(expression.divisor); }
    public Boolean visit(Negation expression) { return literalProbeMiss() ? false : detect(expression.operand); }
    public Boolean visit(Modulo expression) { return literalProbeMiss() ? false : literalZero(expression.right) || detect(expression.left) || detect(expression.right); }
    public Boolean visit(Exponentiation expression) { return literalProbeMiss() ? false : detect(expression.base) || detect(expression.exponent); }
    public Boolean visit(Equality expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(Inequality expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(LessThan expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(GreaterThan expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(LessThanOrEqual expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(GreaterThanOrEqual expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(Conjunction expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(Disjunction expression) { return literalProbeMiss() ? false : detect(expression.left) || detect(expression.right); }
    public Boolean visit(LogicalNot expression) { return literalProbeMiss() ? false : detect(expression.operand); }
    public Boolean visit(Conditional expression) { return literalProbeMiss() ? false : detect(expression.condition) || detect(expression.whenTrue) || detect(expression.whenFalse); }

    public Boolean visit(FunctionCall expression) {
        if (literalProbeMiss()) {
            return false;
        }
        if (detect(expression.callee)) {
            return true;
        }
        for (var argument : expression.arguments) {
            if (detect(argument)) {
                return true;
            }
        }
        return false;
    }
}