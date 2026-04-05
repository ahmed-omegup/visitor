package lib.visitors;

import lib.expression.*;

public class JavaLikeExpressionEmitter extends AbstractExpressionFunction<String> {
    JavaLikeExpressionEmitter() {}

    public String apply(Expression expression) {
        return emit(expression);
    }
    private String emit(Expression expression) {
        String result = visitExpression(expression);
        return result;
    }

    public String visit(Literal expression) { return expression.value; }
    public String visit(VariableReference expression) { return expression.name; }
    public String visit(Addition expression) { return "(" + emit(expression.left) + " + " + emit(expression.right) + ")"; }
    public String visit(Subtraction expression) { return "(" + emit(expression.left) + " - " + emit(expression.right) + ")"; }
    public String visit(Multiplication expression) { return "(" + emit(expression.left) + " * " + emit(expression.right) + ")"; }
    public String visit(Division expression) { return "(" + emit(expression.dividend) + " / " + emit(expression.divisor) + ")"; }
    public String visit(Negation expression) { return "(-" + emit(expression.operand) + ")"; }
    public String visit(Modulo expression) { return "(" + emit(expression.left) + " % " + emit(expression.right) + ")"; }
    public String visit(Exponentiation expression) { return "Math.pow(" + emit(expression.base) + ", " + emit(expression.exponent) + ")"; }
    public String visit(Equality expression) { return "(" + emit(expression.left) + " == " + emit(expression.right) + ")"; }
    public String visit(Inequality expression) { return "(" + emit(expression.left) + " != " + emit(expression.right) + ")"; }
    public String visit(LessThan expression) { return "(" + emit(expression.left) + " < " + emit(expression.right) + ")"; }
    public String visit(GreaterThan expression) { return "(" + emit(expression.left) + " > " + emit(expression.right) + ")"; }
    public String visit(LessThanOrEqual expression) { return "(" + emit(expression.left) + " <= " + emit(expression.right) + ")"; }
    public String visit(GreaterThanOrEqual expression) { return "(" + emit(expression.left) + " >= " + emit(expression.right) + ")"; }
    public String visit(Conjunction expression) { return "(" + emit(expression.left) + " && " + emit(expression.right) + ")"; }
    public String visit(Disjunction expression) { return "(" + emit(expression.left) + " || " + emit(expression.right) + ")"; }
    public String visit(LogicalNot expression) { return "(!" + emit(expression.operand) + ")"; }
    public String visit(Conditional expression) {
        return "(" + emit(expression.condition) + " ? " + emit(expression.whenTrue) + " : " + emit(expression.whenFalse) + ")";
    }
    public String visit(FunctionCall expression) {
        var builder = new StringBuilder();
        builder.append(emit(expression.callee)).append('(');
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            if (index > 0) {
                builder.append(", ");
            }
            builder.append(emit(iter.next()));
        }
        return builder.append(')').toString();
    }

}