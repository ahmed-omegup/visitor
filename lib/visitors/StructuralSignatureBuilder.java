package lib.visitors;

import lib.expression.*;

public class StructuralSignatureBuilder extends AbstractExpressionFunction<String> {
    StructuralSignatureBuilder() {}

    public String apply(Expression expression) {
        return build(expression);
    }
    private String build(Expression expression) {
        String result = visitExpression(expression);
        return result;
    }

    public String visit(Literal expression) { return "Literal"; }
    public String visit(VariableReference expression) { return "VariableReference"; }
    public String visit(Addition expression) { return "Addition(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(Subtraction expression) { return "Subtraction(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(Multiplication expression) { return "Multiplication(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(Division expression) { return "Division(" + build(expression.dividend) + ',' + build(expression.divisor) + ')'; }
    public String visit(Negation expression) { return "Negation(" + build(expression.operand) + ')'; }
    public String visit(Modulo expression) { return "Modulo(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(Exponentiation expression) { return "Exponentiation(" + build(expression.base) + ',' + build(expression.exponent) + ')'; }
    public String visit(Equality expression) { return "Equality(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(Inequality expression) { return "Inequality(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(LessThan expression) { return "LessThan(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(GreaterThan expression) { return "GreaterThan(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(LessThanOrEqual expression) { return "LessThanOrEqual(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(GreaterThanOrEqual expression) { return "GreaterThanOrEqual(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(Conjunction expression) { return "Conjunction(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(Disjunction expression) { return "Disjunction(" + build(expression.left) + ',' + build(expression.right) + ')'; }
    public String visit(LogicalNot expression) { return "LogicalNot(" + build(expression.operand) + ')'; }
    public String visit(Conditional expression) {
        return "Conditional(" + build(expression.condition) + ',' + build(expression.whenTrue) + ',' + build(expression.whenFalse) + ')';
    }
    public String visit(FunctionCall expression) {
        var builder = new StringBuilder();
        builder.append("FunctionCall(").append(build(expression.callee));
        for (var argument : expression.arguments) {
            builder.append(',').append(build(argument));
        }
        return builder.append(')').toString();
    }

}