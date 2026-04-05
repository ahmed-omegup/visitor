package lib.visitors;

import lib.expression.*;

public class TreeDiagramPrinter extends AbstractExpressionFunction<String> {
    TreeDiagramPrinter() {}
    private StringBuilder builder;
    private String prefix;
    private boolean last;

    public String apply(Expression expression) {
        var builder = new StringBuilder();
        append(expression, builder, "", true);
        return builder.toString();
    }
    private void append(Expression expression, StringBuilder builder, String prefix, boolean last) {
        StringBuilder previousBuilder = this.builder;
        this.builder = builder;
        String previousPrefix = this.prefix;
        this.prefix = prefix;
        boolean previousLast = this.last;
        this.last = last;
        visitExpression(expression);
        this.last = previousLast;
        this.prefix = previousPrefix;
        this.builder = previousBuilder;
    }

    private void line(String label) {
        builder.append(prefix).append(last ? "└── " : "├── ").append(label).append('\n');
    }

    public String visit(Literal expression) { line("Literal(" + expression.value + ")");
        return null;
    }

    public String visit(VariableReference expression) { line("VariableReference(" + expression.name + ")");
        return null;
    }

    public String visit(Addition expression) { line("Addition");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Subtraction expression) { line("Subtraction");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Multiplication expression) { line("Multiplication");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Division expression) { line("Division");
        append(expression.dividend, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.divisor, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Negation expression) { line("Negation");
        append(expression.operand, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Modulo expression) { line("Modulo");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Exponentiation expression) { line("Exponentiation");
        append(expression.base, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.exponent, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Equality expression) { line("Equality");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Inequality expression) { line("Inequality");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(LessThan expression) { line("LessThan");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(GreaterThan expression) { line("GreaterThan");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(LessThanOrEqual expression) { line("LessThanOrEqual");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(GreaterThanOrEqual expression) { line("GreaterThanOrEqual");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Conjunction expression) { line("Conjunction");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Disjunction expression) { line("Disjunction");
        append(expression.left, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.right, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(LogicalNot expression) { line("LogicalNot");
        append(expression.operand, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(Conditional expression) { line("Conditional");
        append(expression.condition, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.whenTrue, builder, prefix + (last ? "    " : "│   "), false);
        append(expression.whenFalse, builder, prefix + (last ? "    " : "│   "), true);
        return null;
    }

    public String visit(FunctionCall expression) { line("FunctionCall");
        append(expression.callee, builder, prefix + (last ? "    " : "│   "), expression.arguments.length == 0);
        for (int index = 0; index < expression.arguments.length; index++) {
            append(expression.arguments[index], builder, prefix + (last ? "    " : "│   "), index == expression.arguments.length - 1);
        }
        return null;
    }

}