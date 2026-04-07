package lib.handlers;

import lib.expression.*;

public class YamlExpressionExporter extends AbstractExpressionFunction<String> {
    YamlExpressionExporter() {}
    private StringBuilder builder;
    private int depth;

    public String apply(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, 0);
        return builder.toString();
    }
    private void append(Expression expression, StringBuilder builder, int depth) {
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        int previousDepth = this.depth;
        this.depth = depth;
        visitExpression(expression);
        this.depth = previousDepth;
        this.builder = previousBuilder;
    }

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

    public String visit(Literal expression) { header("Literal");
        builder.append(indent()).append("value: ").append(quote(expression.value)).append('\n');
        return null;
    }

    public String visit(VariableReference expression) { header("VariableReference");
        builder.append(indent()).append("name: ").append(quote(expression.name)).append('\n');
        return null;
    }

    public String visit(Addition expression) { header("Addition"); children(expression.left, expression.right); return null; }
    public String visit(Subtraction expression) { header("Subtraction"); children(expression.left, expression.right); return null; }
    public String visit(Multiplication expression) { header("Multiplication"); children(expression.left, expression.right); return null; }
    public String visit(Division expression) { header("Division"); children(expression.dividend, expression.divisor); return null; }
    public String visit(Negation expression) { header("Negation"); children(expression.operand); return null; }
    public String visit(Modulo expression) { header("Modulo"); children(expression.left, expression.right); return null; }
    public String visit(Exponentiation expression) { header("Exponentiation"); children(expression.base, expression.exponent); return null; }
    public String visit(Equality expression) { header("Equality"); children(expression.left, expression.right); return null; }
    public String visit(Inequality expression) { header("Inequality"); children(expression.left, expression.right); return null; }
    public String visit(LessThan expression) { header("LessThan"); children(expression.left, expression.right); return null; }
    public String visit(GreaterThan expression) { header("GreaterThan"); children(expression.left, expression.right); return null; }
    public String visit(LessThanOrEqual expression) { header("LessThanOrEqual"); children(expression.left, expression.right); return null; }
    public String visit(GreaterThanOrEqual expression) { header("GreaterThanOrEqual"); children(expression.left, expression.right); return null; }
    public String visit(Conjunction expression) { header("Conjunction"); children(expression.left, expression.right); return null; }
    public String visit(Disjunction expression) { header("Disjunction"); children(expression.left, expression.right); return null; }
    public String visit(LogicalNot expression) { header("LogicalNot"); children(expression.operand); return null; }
    public String visit(Conditional expression) { header("Conditional"); children(expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public String visit(FunctionCall expression) { header("FunctionCall");
        builder.append(indent()).append("children:\n");
        builder.append(indent()).append("  -\n");
        append(expression.callee, builder, depth + 2);
        for (var argument : expression.arguments) {
            builder.append(indent()).append("  -\n");
            append(argument, builder, depth + 2);
        }
        return null;
    }

}