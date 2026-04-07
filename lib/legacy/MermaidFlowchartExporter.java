package lib.legacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import static java.util.List.of;

import lib.expression.*;

public class MermaidFlowchartExporter extends AbstractExpressionFunction<String> {
    MermaidFlowchartExporter() {}
    private StringBuilder builder;
    private IdentityHashMap<Expression, String> ids;
    private String id;

    public String apply(Expression expression) {
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
        StringBuilder previousBuilder = this.builder;
        IdentityHashMap<Expression, String> previousIds = this.ids;
        String previousId = this.id;
        this.builder = builder;
        this.ids = ids;
        this.id = id;
        visitExpression(expression);
        this.id = previousId;
        this.ids = previousIds;
        this.builder = previousBuilder;
    }

    private String label(String text) {
        return text.replace("\"", "\\\"");
    }

    private void leaf(String text) {
        builder.append("  ").append(id).append("[\"").append(label(text)).append("\"]\n");
    }

    private void node(String text, Expression... children) {
        node(text, Arrays.asList(children));
    }
    private void node(String text, List<Expression> children) {
        builder.append("  ").append(id).append("[\"").append(label(text)).append("\"]\n");
        for (var child : children) {
            append(child, builder, ids);
            builder.append("  ").append(id).append(" --> ").append(ids.get(child)).append('\n');
        }
    }

    public String visit(Literal expression) { leaf("Literal(" + expression.value + ")"); return null; }
    public String visit(VariableReference expression) { leaf("VariableReference(" + expression.name + ")"); return null; }
    public String visit(Addition expression) { node("Addition", expression.left, expression.right); return null; }
    public String visit(Subtraction expression) { node("Subtraction", expression.left, expression.right); return null; }
    public String visit(Multiplication expression) { node("Multiplication", expression.left, expression.right); return null; }
    public String visit(Division expression) { node("Division", expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) { node("Negation", expression.operand); return null; }
    public String visit(Modulo expression) { node("Modulo", expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) { node("Exponentiation", expression.base, expression.exponent); return null; }
    public String visit(Equality expression) { node("Equality", expression.left, expression.right); return null; }
    public String visit(Inequality expression) { node("Inequality", expression.left, expression.right); return null; }
    public String visit(LessThan expression) { node("LessThan", expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) { node("GreaterThan", expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) { node("LessThanOrEqual", expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { node("GreaterThanOrEqual", expression.left, expression.right); return null; }
    public String visit(Conjunction expression) { node("Conjunction", expression.left, expression.right); return null; }
    public String visit(Disjunction expression) { node("Disjunction", expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) { node("LogicalNot", expression.operand); return null; }
    public String visit(Conditional expression) { node("Conditional", expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public String visit(FunctionCall expression) {
        var children = new ArrayList<Expression>(expression.arguments.size() + 1);
        children.add(expression.callee);
        children.addAll(expression.arguments);
        node("FunctionCall", children);
        return null;
    }
}