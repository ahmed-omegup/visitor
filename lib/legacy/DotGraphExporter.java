package lib.legacy;

import java.util.IdentityHashMap;

import lib.expression.*;

public class DotGraphExporter extends AbstractExpressionFunction<String> {
    DotGraphExporter() {}
    private StringBuilder builder;
    private IdentityHashMap<Expression, Integer> ids;
    private int id;

    public String apply(Expression expression) {
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
        StringBuilder previousBuilder = this.builder;
        IdentityHashMap<Expression, Integer> previousIds = this.ids;
        int previousId = this.id;
        this.builder = builder;
        this.ids = ids;
        this.id = id;
        visitExpression(expression);
        this.id = previousId;
        this.ids = previousIds;
        this.builder = previousBuilder;
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

    public String visit(Literal expression) { node("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { node("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { node("Addition"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Subtraction expression) { node("Subtraction"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Multiplication expression) { node("Multiplication"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Division expression) { node("Division"); edge(expression.dividend); edge(expression.divisor); return null; }
    public String visit(Negation expression) { node("Negation"); edge(expression.operand); return null; }
    public String visit(Modulo expression) { node("Modulo"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Exponentiation expression) { node("Exponentiation"); edge(expression.base); edge(expression.exponent); return null; }
    public String visit(Equality expression) { node("Equality"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Inequality expression) { node("Inequality"); edge(expression.left); edge(expression.right); return null; }
    public String visit(LessThan expression) { node("LessThan"); edge(expression.left); edge(expression.right); return null; }
    public String visit(GreaterThan expression) { node("GreaterThan"); edge(expression.left); edge(expression.right); return null; }
    public String visit(LessThanOrEqual expression) { node("LessThanOrEqual"); edge(expression.left); edge(expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { node("GreaterThanOrEqual"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Conjunction expression) { node("Conjunction"); edge(expression.left); edge(expression.right); return null; }
    public String visit(Disjunction expression) { node("Disjunction"); edge(expression.left); edge(expression.right); return null; }
    public String visit(LogicalNot expression) { node("LogicalNot"); edge(expression.operand); return null; }
    public String visit(Conditional expression) { node("Conditional"); edge(expression.condition); edge(expression.whenTrue); edge(expression.whenFalse); return null; }

    public String visit(FunctionCall expression) { node("FunctionCall");
        edge(expression.callee);
        for (var argument : expression.arguments) {
            edge(argument);
        }
        return null;
    }
}