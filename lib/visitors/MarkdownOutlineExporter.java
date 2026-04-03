package lib.visitors;

import lib.expression.*;

public class MarkdownOutlineExporter implements Visitor<String> {
    MarkdownOutlineExporter() {}

    private boolean active;
    private StringBuilder builder;
    private int depth;

    public String handle(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, 0);
        return builder.toString();
    }
    private void append(Expression expression, StringBuilder builder, int depth) {
        boolean previousActive = this.active;
        this.active = true;
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        int previousDepth = this.depth;
        this.depth = depth;
        expression.accept(this);
        this.depth = previousDepth;
        this.builder = previousBuilder;
        this.active = previousActive;
    }

    private void line(String value) {
        builder.append("  ".repeat(depth)).append("- ").append(value).append('\n');
    }

    public String visit(Literal expression) {
            if (!active) { return handle(expression); }
        line("Literal(" + expression.value + ")");
        return null;
    }

    public String visit(VariableReference expression) {
            if (!active) { return handle(expression); }
        line("VariableReference(" + expression.name + ")");
        return null;
    }

    public String visit(Addition expression) {
            if (!active) { return handle(expression); }
        line("Addition");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); }
        line("Subtraction");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); }
        line("Multiplication");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Division expression) {
            if (!active) { return handle(expression); }
        line("Division");
        append(expression.dividend, builder, depth + 1);
        append(expression.divisor, builder, depth + 1);
        return null;
    }

    public String visit(Negation expression) {
            if (!active) { return handle(expression); }
        line("Negation");
        append(expression.operand, builder, depth + 1);
        return null;
    }

    public String visit(Modulo expression) {
            if (!active) { return handle(expression); }
        line("Modulo");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); }
        line("Exponentiation");
        append(expression.base, builder, depth + 1);
        append(expression.exponent, builder, depth + 1);
        return null;
    }

    public String visit(Equality expression) {
            if (!active) { return handle(expression); }
        line("Equality");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Inequality expression) {
            if (!active) { return handle(expression); }
        line("Inequality");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(LessThan expression) {
            if (!active) { return handle(expression); }
        line("LessThan");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); }
        line("GreaterThan");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); }
        line("LessThanOrEqual");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); }
        line("GreaterThanOrEqual");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); }
        line("Conjunction");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); }
        line("Disjunction");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); }
        line("LogicalNot");
        append(expression.operand, builder, depth + 1);
        return null;
    }

    public String visit(Conditional expression) {
            if (!active) { return handle(expression); }
        line("Conditional");
        append(expression.condition, builder, depth + 1);
        append(expression.whenTrue, builder, depth + 1);
        append(expression.whenFalse, builder, depth + 1);
        return null;
    }

    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        line("FunctionCall");
        append(expression.callee, builder, depth + 1);
        for (var argument : expression.arguments) {
            append(argument, builder, depth + 1);
        }
        return null;
    }

}