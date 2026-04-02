package lib.handlers;

import java.util.IdentityHashMap;

import lib.expression.*;

public class MermaidFlowchartExporter {
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

        expression.accept(new Visitor<Void>() {
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

            public Void visit(Literal expression) { leaf("Literal(" + expression.value + ")"); return null; }
            public Void visit(VariableReference expression) { leaf("VariableReference(" + expression.name + ")"); return null; }
            public Void visit(Addition expression) { node("Addition", expression.left, expression.right); return null; }
            public Void visit(Subtraction expression) { node("Subtraction", expression.left, expression.right); return null; }
            public Void visit(Multiplication expression) { node("Multiplication", expression.left, expression.right); return null; }
            public Void visit(Division expression) { node("Division", expression.dividend, expression.divisor); return null; }
            public Void visit(Negation expression) { node("Negation", expression.operand); return null; }
            public Void visit(Modulo expression) { node("Modulo", expression.left, expression.right); return null; }
            public Void visit(Exponentiation expression) { node("Exponentiation", expression.base, expression.exponent); return null; }
            public Void visit(Equality expression) { node("Equality", expression.left, expression.right); return null; }
            public Void visit(Inequality expression) { node("Inequality", expression.left, expression.right); return null; }
            public Void visit(LessThan expression) { node("LessThan", expression.left, expression.right); return null; }
            public Void visit(GreaterThan expression) { node("GreaterThan", expression.left, expression.right); return null; }
            public Void visit(LessThanOrEqual expression) { node("LessThanOrEqual", expression.left, expression.right); return null; }
            public Void visit(GreaterThanOrEqual expression) { node("GreaterThanOrEqual", expression.left, expression.right); return null; }
            public Void visit(Conjunction expression) { node("Conjunction", expression.left, expression.right); return null; }
            public Void visit(Disjunction expression) { node("Disjunction", expression.left, expression.right); return null; }
            public Void visit(LogicalNot expression) { node("LogicalNot", expression.operand); return null; }
            public Void visit(Conditional expression) { node("Conditional", expression.condition, expression.whenTrue, expression.whenFalse); return null; }
            public Void visit(FunctionCall expression) {
                var children = new Expression[expression.arguments.length + 1];
                children[0] = expression.callee;
                System.arraycopy(expression.arguments, 0, children, 1, expression.arguments.length);
                node("FunctionCall", children);
                return null;
            }
        });
    }
}