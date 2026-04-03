package lib.handlers;

import lib.expression.*;

public class TreeDiagramPrinter implements Visitor<String> {
    TreeDiagramPrinter() {}

    private boolean active;
    private StringBuilder builder;
    private String prefix;
    private boolean last;

    public String handle(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, "", true);
        return builder.toString();
    }
    private void append(Expression expression, StringBuilder builder, String prefix, boolean last) {
        boolean previousActive = this.active;
        this.active = true;
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        String previousPrefix = this.prefix;
        this.prefix = prefix;
        boolean previousLast = this.last;
        this.last = last;
        expression.accept(this);
        this.last = previousLast;
        this.prefix = previousPrefix;
        this.builder = previousBuilder;
        this.active = previousActive;
    }

    private void line(String label) {
        builder.append(prefix).append(last ? "└── " : "├── ").append(label).append('\n');
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
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); }
        line("Subtraction");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); }
        line("Multiplication");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Division expression) {
            if (!active) { return handle(expression); }
        line("Division");
        append(expression.dividend, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.divisor, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Negation expression) {
            if (!active) { return handle(expression); }
        line("Negation");
        append(expression.operand, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Modulo expression) {
            if (!active) { return handle(expression); }
        line("Modulo");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); }
        line("Exponentiation");
        append(expression.base, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.exponent, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Equality expression) {
            if (!active) { return handle(expression); }
        line("Equality");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Inequality expression) {
            if (!active) { return handle(expression); }
        line("Inequality");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(LessThan expression) {
            if (!active) { return handle(expression); }
        line("LessThan");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); }
        line("GreaterThan");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); }
        line("LessThanOrEqual");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); }
        line("GreaterThanOrEqual");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); }
        line("Conjunction");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); }
        line("Disjunction");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); }
        line("LogicalNot");
        append(expression.operand, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Conditional expression) {
            if (!active) { return handle(expression); }
        line("Conditional");
        append(expression.condition, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.whenTrue, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.whenFalse, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        line("FunctionCall");
        append(expression.callee, builder, prefix + (last ? "    " : "│   "), expression.arguments.length == 0);
        for (int index = 0; index < expression.arguments.length; index++) {
            append(expression.arguments[index], builder, prefix + (last ? "    " : "│   "), index == expression.arguments.length - 1);
        }
        return null;
    }

}