package lib.handlers;

import lib.expression.*;

public class YamlExpressionExporter {
    public String handle(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, 0);
        return builder.toString();
    }

    private void append(Expression expression, StringBuilder builder, int depth) {
        expression.accept(new Visitor<Void>() {
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

            public Void visit(Literal expression) {
                header("Literal");
                builder.append(indent()).append("value: ").append(quote(expression.value)).append('\n');
                return null;
            }

            public Void visit(VariableReference expression) {
                header("VariableReference");
                builder.append(indent()).append("name: ").append(quote(expression.name)).append('\n');
                return null;
            }

            public Void visit(Addition expression) { header("Addition"); children(expression.left, expression.right); return null; }
            public Void visit(Subtraction expression) { header("Subtraction"); children(expression.left, expression.right); return null; }
            public Void visit(Multiplication expression) { header("Multiplication"); children(expression.left, expression.right); return null; }
            public Void visit(Division expression) { header("Division"); children(expression.dividend, expression.divisor); return null; }
            public Void visit(Negation expression) { header("Negation"); children(expression.operand); return null; }
            public Void visit(Modulo expression) { header("Modulo"); children(expression.left, expression.right); return null; }
            public Void visit(Exponentiation expression) { header("Exponentiation"); children(expression.base, expression.exponent); return null; }
            public Void visit(Equality expression) { header("Equality"); children(expression.left, expression.right); return null; }
            public Void visit(Inequality expression) { header("Inequality"); children(expression.left, expression.right); return null; }
            public Void visit(LessThan expression) { header("LessThan"); children(expression.left, expression.right); return null; }
            public Void visit(GreaterThan expression) { header("GreaterThan"); children(expression.left, expression.right); return null; }
            public Void visit(LessThanOrEqual expression) { header("LessThanOrEqual"); children(expression.left, expression.right); return null; }
            public Void visit(GreaterThanOrEqual expression) { header("GreaterThanOrEqual"); children(expression.left, expression.right); return null; }
            public Void visit(Conjunction expression) { header("Conjunction"); children(expression.left, expression.right); return null; }
            public Void visit(Disjunction expression) { header("Disjunction"); children(expression.left, expression.right); return null; }
            public Void visit(LogicalNot expression) { header("LogicalNot"); children(expression.operand); return null; }
            public Void visit(Conditional expression) { header("Conditional"); children(expression.condition, expression.whenTrue, expression.whenFalse); return null; }

            public Void visit(FunctionCall expression) {
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
        });
    }
}