package lib.handlers;

import lib.expression.*;

public class ZeroDivisionRiskDetector extends AbstractHandlerVisitor<Boolean> {
    ZeroDivisionRiskDetector() {}

    public Boolean handle(Expression expression) {
        return detect(expression);
    }

    private Boolean detect(Expression expression) {
        return expression.accept(new Visitor<Boolean>() {
            private boolean literalZero(Expression expression) {
                return expression.accept(new Visitor<Boolean>() {
                    public Boolean visit(Literal expression) { return "0".equals(expression.value); }
                    public Boolean visit(VariableReference expression) { return false; }
                    public Boolean visit(Addition expression) { return false; }
                    public Boolean visit(Subtraction expression) { return false; }
                    public Boolean visit(Multiplication expression) { return false; }
                    public Boolean visit(Division expression) { return false; }
                    public Boolean visit(Negation expression) { return false; }
                    public Boolean visit(Modulo expression) { return false; }
                    public Boolean visit(Exponentiation expression) { return false; }
                    public Boolean visit(Equality expression) { return false; }
                    public Boolean visit(Inequality expression) { return false; }
                    public Boolean visit(LessThan expression) { return false; }
                    public Boolean visit(GreaterThan expression) { return false; }
                    public Boolean visit(LessThanOrEqual expression) { return false; }
                    public Boolean visit(GreaterThanOrEqual expression) { return false; }
                    public Boolean visit(Conjunction expression) { return false; }
                    public Boolean visit(Disjunction expression) { return false; }
                    public Boolean visit(LogicalNot expression) { return false; }
                    public Boolean visit(Conditional expression) { return false; }
                    public Boolean visit(FunctionCall expression) { return false; }
                });
            }

            public Boolean visit(Literal expression) { return false; }
            public Boolean visit(VariableReference expression) { return false; }
            public Boolean visit(Addition expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(Subtraction expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(Multiplication expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(Division expression) { return literalZero(expression.divisor) || detect(expression.dividend) || detect(expression.divisor); }
            public Boolean visit(Negation expression) { return detect(expression.operand); }
            public Boolean visit(Modulo expression) { return literalZero(expression.right) || detect(expression.left) || detect(expression.right); }
            public Boolean visit(Exponentiation expression) { return detect(expression.base) || detect(expression.exponent); }
            public Boolean visit(Equality expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(Inequality expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(LessThan expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(GreaterThan expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(LessThanOrEqual expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(GreaterThanOrEqual expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(Conjunction expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(Disjunction expression) { return detect(expression.left) || detect(expression.right); }
            public Boolean visit(LogicalNot expression) { return detect(expression.operand); }
            public Boolean visit(Conditional expression) { return detect(expression.condition) || detect(expression.whenTrue) || detect(expression.whenFalse); }
            public Boolean visit(FunctionCall expression) {
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
        });
    }
}