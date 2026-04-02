package lib.handlers;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.Expression;
import lib.expression.FunctionCall;
import lib.expression.GreaterThan;
import lib.expression.GreaterThanOrEqual;
import lib.expression.Inequality;
import lib.expression.LessThan;
import lib.expression.LessThanOrEqual;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Modulo;
import lib.expression.Multiplication;
import lib.expression.Negation;
import lib.expression.Subtraction;
import lib.expression.VariableReference;
import lib.expression.Visitor;

public class TreeDiagramPrinter {
    public String handle(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, "", true);
        return builder.toString();
    }

    private void append(Expression expression, StringBuilder builder, String prefix, boolean last) {
        expression.accept(new Visitor<Void>() {
            private void line(String label) {
                builder.append(prefix).append(last ? "└── " : "├── ").append(label).append('\n');
            }

            public Void visit(Literal expression) {
                line("Literal(" + expression.value + ")");
                return null;
            }

            public Void visit(VariableReference expression) {
                line("VariableReference(" + expression.name + ")");
                return null;
            }

            public Void visit(Addition expression) {
                line("Addition");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Subtraction expression) {
                line("Subtraction");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Multiplication expression) {
                line("Multiplication");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Division expression) {
                line("Division");
                append(expression.dividend, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.divisor, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Negation expression) {
                line("Negation");
                append(expression.operand, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Modulo expression) {
                line("Modulo");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Exponentiation expression) {
                line("Exponentiation");
                append(expression.base, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.exponent, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Equality expression) {
                line("Equality");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Inequality expression) {
                line("Inequality");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(LessThan expression) {
                line("LessThan");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(GreaterThan expression) {
                line("GreaterThan");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(LessThanOrEqual expression) {
                line("LessThanOrEqual");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(GreaterThanOrEqual expression) {
                line("GreaterThanOrEqual");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Conjunction expression) {
                line("Conjunction");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Disjunction expression) {
                line("Disjunction");
                append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(LogicalNot expression) {
                line("LogicalNot");
                append(expression.operand, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(Conditional expression) {
                line("Conditional");
                append(expression.condition, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.whenTrue, builder, prefix + (last ? "    " : "│   "), false);
                append(expression.whenFalse, builder, prefix + (last ? "    " : "│   "), true);
                return null;
            }

            public Void visit(FunctionCall expression) {
                line("FunctionCall");
                append(expression.callee, builder, prefix + (last ? "    " : "│   "), expression.arguments.length == 0);
                for (int index = 0; index < expression.arguments.length; index++) {
                    append(expression.arguments[index], builder, prefix + (last ? "    " : "│   "), index == expression.arguments.length - 1);
                }
                return null;
            }
        });
    }
}