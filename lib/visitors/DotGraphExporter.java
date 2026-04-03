package lib.visitors;

import java.util.IdentityHashMap;

import lib.expression.*;

public class DotGraphExporter implements Visitor<String> {
    DotGraphExporter() {}

    private boolean active;
    private StringBuilder builder;
    private IdentityHashMap<Expression, Integer> ids;
    private int id;

    public String handle(Expression expression) {
        var ids = new IdentityHashMap<Expression, Integer>();
        var builder = new StringBuilder();
        builder.append("digraph Expression {\n");
        append(expression, builder, ids);
        builder.append("}\n");
        return builder.toString();
    }

    private void append(Expression expression, StringBuilder builder, IdentityHashMap<Expression, Integer> ids) {
        if (ids.containsKey(expression)) {
            return;
        }

        int id = ids.size();
        ids.put(expression, id);

        boolean previousActive = this.active;
        StringBuilder previousBuilder = this.builder;
        IdentityHashMap<Expression, Integer> previousIds = this.ids;
        int previousId = this.id;
        this.active = true;
        this.builder = builder;
        this.ids = ids;
        this.id = id;
        expression.accept(this);
        this.id = previousId;
        this.ids = previousIds;
        this.builder = previousBuilder;
        this.active = previousActive;
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private void node(String label) {
        builder.append("  n").append(id).append(" [label=\"").append(escape(label)).append("\"];\n");
    }

    private void edge(Expression child) {
        append(child, builder, ids);
        builder.append("  n").append(id).append(" -> n").append(ids.get(child)).append(";\n");
    }

    public String visit(Literal expression) { if (!active) { return handle(expression); } node("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { if (!active) { return handle(expression); } node("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { if (!active) { return handle(expression); } node("Addition"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Subtraction expression) { if (!active) { return handle(expression); } node("Subtraction"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Multiplication expression) { if (!active) { return handle(expression); } node("Multiplication"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Division expression) { if (!active) { return handle(expression); } node("Division"); edge(expression.dividend); edge(expression.divisor); return null; }
    public String visit(Negation expression) { if (!active) { return handle(expression); } node("Negation"); edge(expression.operand); return null; }
    public String visit(Modulo expression) { if (!active) { return handle(expression); } node("Modulo"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Exponentiation expression) { if (!active) { return handle(expression); } node("Exponentiation"); edge(expression.base); edge(expression.exponent); return null; }
    public String visit(Equality expression) { if (!active) { return handle(expression); } node("Equality"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Inequality expression) { if (!active) { return handle(expression); } node("Inequality"); edge(expression.left); edge(expression.right); return null; }
    public String visit(LessThan expression) { if (!active) { return handle(expression); } node("LessThan"); edge(expression.left); edge(expression.right); return null; }
    public String visit(GreaterThan expression) { if (!active) { return handle(expression); } node("GreaterThan"); edge(expression.left); edge(expression.right); return null; }
    public String visit(LessThanOrEqual expression) { if (!active) { return handle(expression); } node("LessThanOrEqual"); edge(expression.left); edge(expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { if (!active) { return handle(expression); } node("GreaterThanOrEqual"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Conjunction expression) { if (!active) { return handle(expression); } node("Conjunction"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Disjunction expression) { if (!active) { return handle(expression); } node("Disjunction"); edge(expression.left); edge(expression.right); return null; }
    public String visit(LogicalNot expression) { if (!active) { return handle(expression); } node("LogicalNot"); edge(expression.operand); return null; }
    public String visit(Conditional expression) { if (!active) { return handle(expression); } node("Conditional"); edge(expression.condition); edge(expression.whenTrue); edge(expression.whenFalse); return null; }

    public String visit(FunctionCall expression) {
        if (!active) {
            return handle(expression);
        }
        node("FunctionCall");
        edge(expression.callee);
        for (var argument : expression.arguments) {
            edge(argument);
        }
        return null;
    }
}