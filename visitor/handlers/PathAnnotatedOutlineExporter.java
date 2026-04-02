package visitor.handlers;

import visitor.expression.*;

public class PathAnnotatedOutlineExporter {
    public String handle(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, "0");
        return builder.toString();
    }

    private void append(Expression expression, StringBuilder builder, String path) {
        expression.accept(new Visitor<Void>() {
            private void line(String label) {
                builder.append(path).append(' ').append(label).append('\n');
            }

            public Void visit(Literal expression) { line("Literal(" + expression.value + ")"); return null; }
            public Void visit(VariableReference expression) { line("VariableReference(" + expression.name + ")"); return null; }
            public Void visit(Addition expression) { line("Addition"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Subtraction expression) { line("Subtraction"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Multiplication expression) { line("Multiplication"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Division expression) { line("Division"); append(expression.dividend, builder, path + ".0"); append(expression.divisor, builder, path + ".1"); return null; }
            public Void visit(Negation expression) { line("Negation"); append(expression.operand, builder, path + ".0"); return null; }
            public Void visit(Modulo expression) { line("Modulo"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Exponentiation expression) { line("Exponentiation"); append(expression.base, builder, path + ".0"); append(expression.exponent, builder, path + ".1"); return null; }
            public Void visit(Equality expression) { line("Equality"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Inequality expression) { line("Inequality"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(LessThan expression) { line("LessThan"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(GreaterThan expression) { line("GreaterThan"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(LessThanOrEqual expression) { line("LessThanOrEqual"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(GreaterThanOrEqual expression) { line("GreaterThanOrEqual"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Conjunction expression) { line("Conjunction"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Disjunction expression) { line("Disjunction"); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(LogicalNot expression) { line("LogicalNot"); append(expression.operand, builder, path + ".0"); return null; }
            public Void visit(Conditional expression) { line("Conditional"); append(expression.condition, builder, path + ".0"); append(expression.whenTrue, builder, path + ".1"); append(expression.whenFalse, builder, path + ".2"); return null; }

            public Void visit(FunctionCall expression) {
                line("FunctionCall");
                append(expression.callee, builder, path + ".0");
                for (int index = 0; index < expression.arguments.length; index++) {
                    append(expression.arguments[index], builder, path + "." + (index + 1));
                }
                return null;
            }
        });
    }
}