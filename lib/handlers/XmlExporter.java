package lib.handlers;

import lib.expression.*;

public class XmlExporter {
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

            private String escape(String value) {
                return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
            }

            public Void visit(Literal expression) {
                builder.append(indent()).append("<Literal value=\"").append(escape(expression.value)).append("\"/>\n");
                return null;
            }

            public Void visit(VariableReference expression) {
                builder.append(indent()).append("<VariableReference name=\"").append(escape(expression.name)).append("\"/>\n");
                return null;
            }

            public Void visit(Addition expression) {
                element("Addition", expression.left, expression.right);
                return null;
            }

            public Void visit(Subtraction expression) {
                element("Subtraction", expression.left, expression.right);
                return null;
            }

            public Void visit(Multiplication expression) {
                element("Multiplication", expression.left, expression.right);
                return null;
            }

            public Void visit(Division expression) {
                element("Division", expression.dividend, expression.divisor);
                return null;
            }

            public Void visit(Negation expression) {
                element("Negation", expression.operand);
                return null;
            }

            public Void visit(Modulo expression) {
                element("Modulo", expression.left, expression.right);
                return null;
            }

            public Void visit(Exponentiation expression) {
                element("Exponentiation", expression.base, expression.exponent);
                return null;
            }

            public Void visit(Equality expression) {
                element("Equality", expression.left, expression.right);
                return null;
            }

            public Void visit(Inequality expression) {
                element("Inequality", expression.left, expression.right);
                return null;
            }

            public Void visit(LessThan expression) {
                element("LessThan", expression.left, expression.right);
                return null;
            }

            public Void visit(GreaterThan expression) {
                element("GreaterThan", expression.left, expression.right);
                return null;
            }

            public Void visit(LessThanOrEqual expression) {
                element("LessThanOrEqual", expression.left, expression.right);
                return null;
            }

            public Void visit(GreaterThanOrEqual expression) {
                element("GreaterThanOrEqual", expression.left, expression.right);
                return null;
            }

            public Void visit(Conjunction expression) {
                element("Conjunction", expression.left, expression.right);
                return null;
            }

            public Void visit(Disjunction expression) {
                element("Disjunction", expression.left, expression.right);
                return null;
            }

            public Void visit(LogicalNot expression) {
                element("LogicalNot", expression.operand);
                return null;
            }

            public Void visit(Conditional expression) {
                element("Conditional", expression.condition, expression.whenTrue, expression.whenFalse);
                return null;
            }

            public Void visit(FunctionCall expression) {
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
        });
    }
}