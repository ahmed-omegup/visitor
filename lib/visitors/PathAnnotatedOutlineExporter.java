package lib.visitors;

import lib.expression.*;

public class PathAnnotatedOutlineExporter extends AbstractExpressionFunction<String> {
    PathAnnotatedOutlineExporter() {
    }

    private StringBuilder builder;
    private String path;

    public String apply(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, "0");
        return builder.toString();
    }

    private void append(Expression expression, StringBuilder builder, String path) {
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        String previousPath = this.path;
        this.path = path;
        visitExpression(expression);
        this.path = previousPath;
        this.builder = previousBuilder;
    }

    private void line(String label) {
        builder.append(path).append(' ').append(label).append('\n');
    }

    public String visit(Literal expression) {
        line("Literal(" + expression.value + ")");
        return null;
    }

    public String visit(VariableReference expression) {
        line("VariableReference(" + expression.name + ")");
        return null;
    }

    public String visit(Addition expression) {
        line("Addition");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(Subtraction expression) {
        line("Subtraction");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(Multiplication expression) {
        line("Multiplication");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(Division expression) {
        line("Division");
        append(expression.dividend, builder, path + ".0");
        append(expression.divisor, builder, path + ".1");
        return null;
    }

    public String visit(Negation expression) {
        line("Negation");
        append(expression.operand, builder, path + ".0");
        return null;
    }

    public String visit(Modulo expression) {
        line("Modulo");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(Exponentiation expression) {
        line("Exponentiation");
        append(expression.base, builder, path + ".0");
        append(expression.exponent, builder, path + ".1");
        return null;
    }

    public String visit(Equality expression) {
        line("Equality");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(Inequality expression) {
        line("Inequality");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(LessThan expression) {
        line("LessThan");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(GreaterThan expression) {
        line("GreaterThan");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(LessThanOrEqual expression) {
        line("LessThanOrEqual");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(GreaterThanOrEqual expression) {
        line("GreaterThanOrEqual");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(Conjunction expression) {
        line("Conjunction");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(Disjunction expression) {
        line("Disjunction");
        append(expression.left, builder, path + ".0");
        append(expression.right, builder, path + ".1");
        return null;
    }

    public String visit(LogicalNot expression) {
        line("LogicalNot");
        append(expression.operand, builder, path + ".0");
        return null;
    }

    public String visit(Conditional expression) {
        line("Conditional");
        append(expression.condition, builder, path + ".0");
        append(expression.whenTrue, builder, path + ".1");
        append(expression.whenFalse, builder, path + ".2");
        return null;
    }

    public String visit(FunctionCall expression) {
        line("FunctionCall");
        append(expression.callee, builder, path + ".0");
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            append(iter.next(), builder, path + "." + (index + 1));
        }
        return null;
    }

}