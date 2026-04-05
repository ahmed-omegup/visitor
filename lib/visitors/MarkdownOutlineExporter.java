package lib.visitors;

import lib.expression.*;

public class MarkdownOutlineExporter extends AbstractExpressionFunction<String> {
    MarkdownOutlineExporter() {}
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

    private void line(String value) {
        builder.append("  ".repeat(depth)).append("- ").append(value).append('\n');
    }

    public String visit(Literal expression) { line("Literal(" + expression.value + ")");
        return null;
    }

    public String visit(VariableReference expression) { line("VariableReference(" + expression.name + ")");
        return null;
    }

    public String visit(Addition expression) { line("Addition");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Subtraction expression) { line("Subtraction");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Multiplication expression) { line("Multiplication");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Division expression) { line("Division");
        append(expression.dividend, builder, depth + 1);
        append(expression.divisor, builder, depth + 1);
        return null;
    }

    public String visit(Negation expression) { line("Negation");
        append(expression.operand, builder, depth + 1);
        return null;
    }

    public String visit(Modulo expression) { line("Modulo");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Exponentiation expression) { line("Exponentiation");
        append(expression.base, builder, depth + 1);
        append(expression.exponent, builder, depth + 1);
        return null;
    }

    public String visit(Equality expression) { line("Equality");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Inequality expression) { line("Inequality");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(LessThan expression) { line("LessThan");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(GreaterThan expression) { line("GreaterThan");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(LessThanOrEqual expression) { line("LessThanOrEqual");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(GreaterThanOrEqual expression) { line("GreaterThanOrEqual");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Conjunction expression) { line("Conjunction");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(Disjunction expression) { line("Disjunction");
        append(expression.left, builder, depth + 1);
        append(expression.right, builder, depth + 1);
        return null;
    }

    public String visit(LogicalNot expression) { line("LogicalNot");
        append(expression.operand, builder, depth + 1);
        return null;
    }

    public String visit(Conditional expression) { line("Conditional");
        append(expression.condition, builder, depth + 1);
        append(expression.whenTrue, builder, depth + 1);
        append(expression.whenFalse, builder, depth + 1);
        return null;
    }

    public String visit(FunctionCall expression) { line("FunctionCall");
        append(expression.callee, builder, depth + 1);
        for (var argument : expression.arguments) {
            append(argument, builder, depth + 1);
        }
        return null;
    }

}