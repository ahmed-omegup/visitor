package lib.handlers;

import lib.expression.*;

public class XmlExporter implements Visitor<String> {
    XmlExporter() {}

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

    private String escape(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public String visit(Literal expression) {
            if (!active) { return handle(expression); }
        builder.append(indent()).append("<Literal value=\"").append(escape(expression.value)).append("\"/>\n");
        return null;
    }

    public String visit(VariableReference expression) {
            if (!active) { return handle(expression); }
        builder.append(indent()).append("<VariableReference name=\"").append(escape(expression.name)).append("\"/>\n");
        return null;
    }

    public String visit(Addition expression) {
            if (!active) { return handle(expression); }
        element("Addition", expression.left, expression.right);
        return null;
    }

    public String visit(Subtraction expression) {
            if (!active) { return handle(expression); }
        element("Subtraction", expression.left, expression.right);
        return null;
    }

    public String visit(Multiplication expression) {
            if (!active) { return handle(expression); }
        element("Multiplication", expression.left, expression.right);
        return null;
    }

    public String visit(Division expression) {
            if (!active) { return handle(expression); }
        element("Division", expression.dividend, expression.divisor);
        return null;
    }

    public String visit(Negation expression) {
            if (!active) { return handle(expression); }
        element("Negation", expression.operand);
        return null;
    }

    public String visit(Modulo expression) {
            if (!active) { return handle(expression); }
        element("Modulo", expression.left, expression.right);
        return null;
    }

    public String visit(Exponentiation expression) {
            if (!active) { return handle(expression); }
        element("Exponentiation", expression.base, expression.exponent);
        return null;
    }

    public String visit(Equality expression) {
            if (!active) { return handle(expression); }
        element("Equality", expression.left, expression.right);
        return null;
    }

    public String visit(Inequality expression) {
            if (!active) { return handle(expression); }
        element("Inequality", expression.left, expression.right);
        return null;
    }

    public String visit(LessThan expression) {
            if (!active) { return handle(expression); }
        element("LessThan", expression.left, expression.right);
        return null;
    }

    public String visit(GreaterThan expression) {
            if (!active) { return handle(expression); }
        element("GreaterThan", expression.left, expression.right);
        return null;
    }

    public String visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); }
        element("LessThanOrEqual", expression.left, expression.right);
        return null;
    }

    public String visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); }
        element("GreaterThanOrEqual", expression.left, expression.right);
        return null;
    }

    public String visit(Conjunction expression) {
            if (!active) { return handle(expression); }
        element("Conjunction", expression.left, expression.right);
        return null;
    }

    public String visit(Disjunction expression) {
            if (!active) { return handle(expression); }
        element("Disjunction", expression.left, expression.right);
        return null;
    }

    public String visit(LogicalNot expression) {
            if (!active) { return handle(expression); }
        element("LogicalNot", expression.operand);
        return null;
    }

    public String visit(Conditional expression) {
            if (!active) { return handle(expression); }
        element("Conditional", expression.condition, expression.whenTrue, expression.whenFalse);
        return null;
    }

    public String visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        builder.append(indent()).append("<FunctionCall>\n");
        append(expression.callee, builder, depth + 1);
        for (var argument : expression.arguments) {
            append(argument, builder, depth + 1);
        }
        builder.append(indent()).append("</FunctionCall>\n");
        return null;
    }

    private void element(String name, Expression... children) {
        builder.append(indent()).append('<').append(name).append(">\n");
        for (var child : children) {
            append(child, builder, depth + 1);
        }
        builder.append(indent()).append("</").append(name).append(">\n");
    }

}