package lib.handlers;

import java.util.IdentityHashMap;

import lib.expression.*;

public class DotGraphExporter implements Visitor<String> {
    DotGraphExporter() {}

    private StringBuilder builder;
    private IdentityHashMap<Expression, Integer> ids;

    public String handle(Expression expression) {
        var ids = new IdentityHashMap<Expression, Integer>();
        var builder = new StringBuilder();
        builder.append("digraph Expression {\n");
        append(expression, builder, ids);
        builder.append("}\n");
        return builder.toString();
    }

    private
    private void append(Expression expression, StringBuilder builder, IdentityHashMap<Expression, Integer> ids) {
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        IdentityHashMap<Expression, Integer> previousIds = this.ids;
        this.ids = ids;
        expression.accept(this);
        this.builder = previousBuilder;
        this.ids = previousIds;
    
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

            public Void visit(Literal expression) {
                node("Literal(" + expression.value + ")");
                return null;
            }

            public Void visit(VariableReference expression) {
                node("VariableReference(" + expression.name + ")");
                return null;
            }

            public Void visit(Addition expression) {
                node("Addition");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(Subtraction expression) {
                node("Subtraction");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(Multiplication expression) {
                node("Multiplication");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(Division expression) {
                node("Division");
                edge(expression.dividend);
                edge(expression.divisor);
                return null;
            }

            public Void visit(Negation expression) {
                node("Negation");
                edge(expression.operand);
                return null;
            }

            public Void visit(Modulo expression) {
                node("Modulo");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(Exponentiation expression) {
                node("Exponentiation");
                edge(expression.base);
                edge(expression.exponent);
                return null;
            }

            public Void visit(Equality expression) {
                node("Equality");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(Inequality expression) {
                node("Inequality");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(LessThan expression) {
                node("LessThan");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(GreaterThan expression) {
                node("GreaterThan");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(LessThanOrEqual expression) {
                node("LessThanOrEqual");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(GreaterThanOrEqual expression) {
                node("GreaterThanOrEqual");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(Conjunction expression) {
                node("Conjunction");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(Disjunction expression) {
                node("Disjunction");
                edge(expression.left);
                edge(expression.right);
                return null;
            }

            public Void visit(LogicalNot expression) {
                node("LogicalNot");
                edge(expression.operand);
                return null;
            }

            public Void visit(Conditional expression) {
                node("Conditional");
                edge(expression.condition);
                edge(expression.whenTrue);
                edge(expression.whenFalse);
                return null;
            }

            public Void visit(FunctionCall expression) {
                node("FunctionCall");
                edge(expression.callee);
                for (var argument : expression.arguments) {
                    edge(argument);
                }
                return null;
            }
        
}