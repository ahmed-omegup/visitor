package lib.handlers;

import java.util.StringJoiner;

import lib.expression.*;

public class CsvNodeExporter extends AbstractExpressionFunction<String> {
    CsvNodeExporter() {}
    private StringBuilder builder;
    private String path;

    public String apply(Expression expression) {
        var builder = new StringBuilder();
        builder.append("path,type,detail\n");
        append(expression, builder, "0");
        return builder.toString();
    }
    private void append(Expression expression, StringBuilder builder, String path) {
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        String previousPath = this.path;
        this.path = path;
        visitExpression(expression);
        this.path = previousPath;
        this.builder = previousBuilder;
    }

    private String csv(String value) {
        return '"' + value.replace("\"", "\"\"") + '"';
    }

    private void row(String type, String detail) {
        builder.append(csv(path)).append(',').append(csv(type)).append(',').append(csv(detail)).append('\n');
    }

    public String visit(Literal expression) { row("Literal", expression.value); return null; }
    public String visit(VariableReference expression) { row("VariableReference", expression.name); return null; }
    public String visit(Addition expression) { row("Addition", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Subtraction expression) { row("Subtraction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Multiplication expression) { row("Multiplication", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Division expression) { row("Division", ""); append(expression.dividend, builder, path + ".0"); append(expression.divisor, builder, path + ".1"); return null; }
    public String visit(Negation expression) { row("Negation", ""); append(expression.operand, builder, path + ".0"); return null; }
    public String visit(Modulo expression) { row("Modulo", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Exponentiation expression) { row("Exponentiation", ""); append(expression.base, builder, path + ".0"); append(expression.exponent, builder, path + ".1"); return null; }
    public String visit(Equality expression) { row("Equality", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Inequality expression) { row("Inequality", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LessThan expression) { row("LessThan", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(GreaterThan expression) { row("GreaterThan", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LessThanOrEqual expression) { row("LessThanOrEqual", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(GreaterThanOrEqual expression) { row("GreaterThanOrEqual", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Conjunction expression) { row("Conjunction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(Disjunction expression) { row("Disjunction", ""); append(expression.left, builder, path + ".0"); append(expression.right, builder, path + ".1"); return null; }
    public String visit(LogicalNot expression) { row("LogicalNot", ""); append(expression.operand, builder, path + ".0"); return null; }
    public String visit(Conditional expression) { row("Conditional", ""); append(expression.condition, builder, path + ".0"); append(expression.whenTrue, builder, path + ".1"); append(expression.whenFalse, builder, path + ".2"); return null; }
    public String visit(FunctionCall expression) { row("FunctionCall", Integer.toString(expression.arguments.size()));
        append(expression.callee, builder, path + ".0");
        var iter = expression.arguments.iterator();
        for (int index = 0; iter.hasNext(); index++) {
            append(iter.next(), builder, path + "." + (index + 1));
        }
        return null;
    }

}