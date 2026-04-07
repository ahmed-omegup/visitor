package lib.legacy;

import lib.expression.*;

public class SExpressionExporter extends AbstractExpressionFunction<String> {
    SExpressionExporter() {}

    public String apply(Expression expression) {
        return export(expression);
    }
    private String export(Expression expression) {
        String result = visitExpression(expression);
        return result;
    }

    public String visit(Literal expression) {
        return expression.value;
    }

    public String visit(VariableReference expression) {
        return expression.name;
    }

    public String visit(Addition expression) {
        return "(Addition " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(Subtraction expression) {
        return "(Subtraction " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(Multiplication expression) {
        return "(Multiplication " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(Division expression) {
        return "(Division " + export(expression.dividend) + " " + export(expression.divisor) + ")";
    }

    public String visit(Negation expression) {
        return "(Negation " + export(expression.operand) + ")";
    }

    public String visit(Modulo expression) {
        return "(Modulo " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(Exponentiation expression) {
        return "(Exponentiation " + export(expression.base) + " " + export(expression.exponent) + ")";
    }

    public String visit(Equality expression) {
        return "(Equality " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(Inequality expression) {
        return "(Inequality " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(LessThan expression) {
        return "(LessThan " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(GreaterThan expression) {
        return "(GreaterThan " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(LessThanOrEqual expression) {
        return "(LessThanOrEqual " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(GreaterThanOrEqual expression) {
        return "(GreaterThanOrEqual " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(Conjunction expression) {
        return "(Conjunction " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(Disjunction expression) {
        return "(Disjunction " + export(expression.left) + " " + export(expression.right) + ")";
    }

    public String visit(LogicalNot expression) {
        return "(LogicalNot " + export(expression.operand) + ")";
    }

    public String visit(Conditional expression) {
        return "(Conditional " + export(expression.condition) + " " + export(expression.whenTrue) + " " + export(expression.whenFalse) + ")";
    }

    public String visit(FunctionCall expression) {
        var builder = new StringBuilder();
        builder.append("(FunctionCall ").append(export(expression.callee));
        for (var argument : expression.arguments) {
            builder.append(' ').append(export(argument));
        }
        return builder.append(')').toString();
    }

}