package lib.handlers;

import java.util.StringJoiner;

import lib.expression.*;

public class CsvNodeExporter {
    public String handle(Expression expression) {
        var builder = new StringBuilder();
        builder.append("path,type,detail\n");
        append(expression, builder, "0");
        return builder.toString();
    }

    private void append(Expression expression, StringBuilder builder, String path) {
        expression.accept(new Visitor<Void>() {
            private String csv(String value) {
                return '"' + value.replace("\"", "\"\"") + '"';
            }

            private void row(String type, String detail) {
                builder.append(csv(path)).append(',').append(csv(type)).append(',').append(csv(detail)).append('\n');
            }

            public Void visit(Literal expression) { row("Literal", expression.value); return null; }
            public Void visit(VariableReference expression) { row("VariableReference", expression.name); return null; }
            public Void visit(Addition expression) { row("Addition", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Subtraction expression) { row("Subtraction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Multiplication expression) { row("Multiplication", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Division expression) { row("Division", ""); append(expression.dividend, builder, path + ".0"); append(expression.divisor, builder, path + ".1"); return null; }
            public Void visit(Negation expression) { row("Negation", ""); append(expression.operand, builder, path + ".0"); return null; }
            public Void visit(Modulo expression) { row("Modulo", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Exponentiation expression) { row("Exponentiation", ""); append(expression.base, builder, path + ".0"); append(expression.exponent, builder, path + ".1"); return null; }
            public Void visit(Equality expression) { row("Equality", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Inequality expression) { row("Inequality", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(LessThan expression) { row("LessThan", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(GreaterThan expression) { row("GreaterThan", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(LessThanOrEqual expression) { row("LessThanOrEqual", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(GreaterThanOrEqual expression) { row("GreaterThanOrEqual", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Conjunction expression) { row("Conjunction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(Disjunction expression) { row("Disjunction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
            public Void visit(LogicalNot expression) { row("LogicalNot", ""); append(expression.operand, builder, path + ".0"); return null; }
            public Void visit(Conditional expression) { row("Conditional", ""); append(expression.condition, builder, path + ".0"); append(expression.whenTrue, builder, path + ".1"); append(expression.whenFalse, builder, path + ".2"); return null; }
            public Void visit(FunctionCall expression) {
                row("FunctionCall", Integer.toString(expression.arguments.length));
                append(expression.callee, builder, path + ".0");
                for (int index = 0; index < expression.arguments.length; index++) {
                    append(expression.arguments[index], builder, path + "." + (index + 1));
                }
                return null;
            }
        });
    }
}