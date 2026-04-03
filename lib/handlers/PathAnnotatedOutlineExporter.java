package lib.handlers;

import lib.expression.*;

public class PathAnnotatedOutlineExporter implements Visitor<String> {
    PathAnnotatedOutlineExporter() {}

    private boolean active;
    private StringBuilder builder;
    private String path;

    public String handle(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, "0");
        return builder.toString();
    }
    private void append(Expression expression, StringBuilder builder, String path) {
        boolean previousActive = this.active;
        this.active = true;
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        String previousPath = this.path;
        this.path = path;
        expression.accept(this);
        this.path = previousPath;
        this.builder = previousBuilder;
        this.active = previousActive;
    }

    private void line(String label) {
        builder.append(path).append(' ').append(label).append('\n');
    }

    public String visit(Literal expression) {
            if (!active) { return handle(expression); } line("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) {
            if (!active) { return handle(expression); } line("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) {
            if (!active) { return handle(expression); } line("Addition"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); } line("Subtraction"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); } line("Multiplication"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Division expression) {
            if (!active) { return handle(expression); } line("Division"); append(expression.dividend, builder, path + ".0"); append(expression.divisor, builder, path + ".1"); return null; }
    public String visit(Negation expression) {
            if (!active) { return handle(expression); } line("Negation"); append(expression.operand, builder, path + ".0"); return null; }
    public String visit(Modulo expression) {
            if (!active) { return handle(expression); } line("Modulo"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); } line("Exponentiation"); append(expression.base, builder, path + ".0"); append(expression.exponent, builder, path + ".1"); return null; }
    public String visit(Equality expression) {
            if (!active) { return handle(expression); } line("Equality"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Inequality expression) {
            if (!active) { return handle(expression); } line("Inequality"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LessThan expression) {
            if (!active) { return handle(expression); } line("LessThan"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); } line("GreaterThan"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } line("LessThanOrEqual"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } line("GreaterThanOrEqual"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); } line("Conjunction"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); } line("Disjunction"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); } line("LogicalNot"); append(expression.operand, builder, path + ".0"); return null; }
    public String visit(Conditional expression) {
            if (!active) { return handle(expression); } line("Conditional"); append(expression.condition, builder, path + ".0"); append(expression.whenTrue, builder, path + ".1"); append(expression.whenFalse, builder, path + ".2"); return null; }

    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        line("FunctionCall");
        append(expression.callee, builder, path + ".0");
        for (int index = 0; index < expression.arguments.length; index++) {
            append(expression.arguments[index], builder, path + "." + (index + 1));
        }
        return null;
    }

}