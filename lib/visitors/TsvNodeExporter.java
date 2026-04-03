package lib.visitors;

import lib.expression.*;

public class TsvNodeExporter implements Visitor<String> {
    TsvNodeExporter() {}

    private boolean active;
    private StringBuilder builder;
    private String path;

    public String handle(Expression expression) {
        var builder = new StringBuilder();
        builder.append("path\ttype\tdetail\n");
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

    private void row(String type, String detail) {
        builder.append(path).append('\t').append(type).append('\t').append(detail).append('\n');
    }

    public String visit(Literal expression) {
            if (!active) { return handle(expression); } row("Literal", expression.value); return null; }
    public String visit(VariableReference expression) {
            if (!active) { return handle(expression); } row("VariableReference", expression.name); return null; }
    public String visit(Addition expression) {
            if (!active) { return handle(expression); } row("Addition", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); } row("Subtraction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); } row("Multiplication", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Division expression) {
            if (!active) { return handle(expression); } row("Division", ""); append(expression.dividend, builder, path + ".0"); append(expression.divisor, builder, path + ".1"); return null; }
    public String visit(Negation expression) {
            if (!active) { return handle(expression); } row("Negation", ""); append(expression.operand, builder, path + ".0"); return null; }
    public String visit(Modulo expression) {
            if (!active) { return handle(expression); } row("Modulo", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); } row("Exponentiation", ""); append(expression.base, builder, path + ".0"); append(expression.exponent, builder, path + ".1"); return null; }
    public String visit(Equality expression) {
            if (!active) { return handle(expression); } row("Equality", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Inequality expression) {
            if (!active) { return handle(expression); } row("Inequality", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LessThan expression) {
            if (!active) { return handle(expression); } row("LessThan", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); } row("GreaterThan", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } row("LessThanOrEqual", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } row("GreaterThanOrEqual", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); } row("Conjunction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); } row("Disjunction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); } row("LogicalNot", ""); append(expression.operand, builder, path + ".0"); return null; }
    public String visit(Conditional expression) {
            if (!active) { return handle(expression); } row("Conditional", ""); append(expression.condition, builder, path + ".0"); append(expression.whenTrue, builder, path + ".1"); append(expression.whenFalse, builder, path + ".2"); return null; }
    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        row("FunctionCall", Integer.toString(expression.arguments.length));
        append(expression.callee, builder, path + ".0");
        for (int index = 0; index < expression.arguments.length; index++) {
            append(expression.arguments[index], builder, path + "." + (index + 1));
        }
        return null;
    }

}