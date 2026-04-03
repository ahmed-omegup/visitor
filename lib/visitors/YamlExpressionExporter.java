package lib.visitors;

import lib.expression.*;

public class YamlExpressionExporter implements Visitor<String> {
    YamlExpressionExporter() {}

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

    private String indent() {
        return "  ".repeat(depth);
    }

    private String quote(String value) {
        return '"' + value.replace("\\", "\\\\").replace("\"", "\\\"") + '"';
    }

    private void header(String type) {
        builder.append(indent()).append("type: ").append(type).append('\n');
    }

    private void children(Expression... children) {
        builder.append(indent()).append("children:\n");
        for (var child : children) {
            builder.append(indent()).append("  -\n");
            append(child, builder, depth + 2);
        }
    }

    public String visit(Literal expression) {
            if (!active) { return handle(expression); }
        header("Literal");
        builder.append(indent()).append("value: ").append(quote(expression.value)).append('\n');
        return null;
    }

    public String visit(VariableReference expression) {
            if (!active) { return handle(expression); }
        header("VariableReference");
        builder.append(indent()).append("name: ").append(quote(expression.name)).append('\n');
        return null;
    }

    public String visit(Addition expression) {
            if (!active) { return handle(expression); } header("Addition"); children(expression.left, expression.right); return null; }
    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); } header("Subtraction"); children(expression.left, expression.right); return null; }
    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); } header("Multiplication"); children(expression.left, expression.right); return null; }
    public String visit(Division expression) {
            if (!active) { return handle(expression); } header("Division"); children(expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) {
            if (!active) { return handle(expression); } header("Negation"); children(expression.operand); return null; }
    public String visit(Modulo expression) {
            if (!active) { return handle(expression); } header("Modulo"); children(expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); } header("Exponentiation"); children(expression.base, expression.exponent); return null; }
    public String visit(Equality expression) {
            if (!active) { return handle(expression); } header("Equality"); children(expression.left, expression.right); return null; }
    public String visit(Inequality expression) {
            if (!active) { return handle(expression); } header("Inequality"); children(expression.left, expression.right); return null; }
    public String visit(LessThan expression) {
            if (!active) { return handle(expression); } header("LessThan"); children(expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); } header("GreaterThan"); children(expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } header("LessThanOrEqual"); children(expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } header("GreaterThanOrEqual"); children(expression.left, expression.right); return null; }
    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); } header("Conjunction"); children(expression.left, expression.right); return null; }
    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); } header("Disjunction"); children(expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); } header("LogicalNot"); children(expression.operand); return null; }
    public String visit(Conditional expression) {
            if (!active) { return handle(expression); } header("Conditional"); children(expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        header("FunctionCall");
        builder.append(indent()).append("children:\n");
        builder.append(indent()).append("  -\n");
        append(expression.callee, builder, depth + 2);
        for (var argument : expression.arguments) {
            builder.append(indent()).append("  -\n");
            append(argument, builder, depth + 2);
        }
        return null;
    }

}