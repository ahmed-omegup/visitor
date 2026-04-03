package lib.visitors;

import java.util.IdentityHashMap;

import lib.expression.*;

public class MermaidFlowchartExporter implements Visitor<String> {
    MermaidFlowchartExporter() {}

    private boolean active;
    private StringBuilder builder;
    private IdentityHashMap<Expression, String> ids;
    private String id;

    public String handle(Expression expression) {
        var ids = new IdentityHashMap<Expression, String>();
        var builder = new StringBuilder("flowchart TD\n");
        append(expression, builder, ids);
        return builder.toString();
    }

    private void append(Expression expression, StringBuilder builder, IdentityHashMap<Expression, String> ids) {
        if (ids.containsKey(expression)) {
            return;
        }

        var id = "n" + ids.size();
        ids.put(expression, id);

        boolean previousActive = this.active;
        StringBuilder previousBuilder = this.builder;
        IdentityHashMap<Expression, String> previousIds = this.ids;
        String previousId = this.id;
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

    private String label(String text) {
        return text.replace("\"", "\\\"");
    }

    private void leaf(String text) {
        builder.append("  ").append(id).append("[\"").append(label(text)).append("\"]\n");
    }

    private void node(String text, Expression... children) {
        builder.append("  ").append(id).append("[\"").append(label(text)).append("\"]\n");
        for (var child : children) {
            append(child, builder, ids);
            builder.append("  ").append(id).append(" --> ").append(ids.get(child)).append('\n');
        }
    }

    public String visit(Literal expression) { if (!active) { return handle(expression); } leaf("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { if (!active) { return handle(expression); } leaf("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { if (!active) { return handle(expression); } node("Addition", expression.left, expression.right); return null; }
    public String visit(Subtraction expression) { if (!active) { return handle(expression); } node("Subtraction", expression.left, expression.right); return null; }
    public String visit(Multiplication expression) { if (!active) { return handle(expression); } node("Multiplication", expression.left, expression.right); return null; }
    public String visit(Division expression) { if (!active) { return handle(expression); } node("Division", expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) { if (!active) { return handle(expression); } node("Negation", expression.operand); return null; }
    public String visit(Modulo expression) { if (!active) { return handle(expression); } node("Modulo", expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) { if (!active) { return handle(expression); } node("Exponentiation", expression.base, expression.exponent); return null; }
    public String visit(Equality expression) { if (!active) { return handle(expression); } node("Equality", expression.left, expression.right); return null; }
    public String visit(Inequality expression) { if (!active) { return handle(expression); } node("Inequality", expression.left, expression.right); return null; }
    public String visit(LessThan expression) { if (!active) { return handle(expression); } node("LessThan", expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) { if (!active) { return handle(expression); } node("GreaterThan", expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) { if (!active) { return handle(expression); } node("LessThanOrEqual", expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { if (!active) { return handle(expression); } node("GreaterThanOrEqual", expression.left, expression.right); return null; }
    public String visit(Conjunction expression) { if (!active) { return handle(expression); } node("Conjunction", expression.left, expression.right); return null; }
    public String visit(Disjunction expression) { if (!active) { return handle(expression); } node("Disjunction", expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) { if (!active) { return handle(expression); } node("LogicalNot", expression.operand); return null; }
    public String visit(Conditional expression) { if (!active) { return handle(expression); } node("Conditional", expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public String visit(FunctionCall expression) {
        if (!active) {
            return handle(expression);
        }
        var children = new Expression[expression.arguments.length + 1];
        children[0] = expression.callee;
        System.arraycopy(expression.arguments, 0, children, 1, expression.arguments.length);
        node("FunctionCall", children);
        return null;
    }
}