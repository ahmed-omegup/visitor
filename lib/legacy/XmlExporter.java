package lib.legacy;

import lib.expression.*;

public class XmlExporter extends AbstractExpressionFunction<String> {
    XmlExporter() {}
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

    private String escape(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    public String visit(Literal expression) { builder.append(indent()).append("<Literal value=\"").append(escape(expression.value)).append("\"/>\n");
        return null;
    }

    public String visit(VariableReference expression) { builder.append(indent()).append("<VariableReference name=\"").append(escape(expression.name)).append("\"/>\n");
        return null;
    }

    public String visit(Addition expression) { element("Addition", expression.left, expression.right);
        return null;
    }

    public String visit(Subtraction expression) { element("Subtraction", expression.left, expression.right);
        return null;
    }

    public String visit(Multiplication expression) { element("Multiplication", expression.left, expression.right);
        return null;
    }

    public String visit(Division expression) { element("Division", expression.dividend, expression.divisor);
        return null;
    }

    public String visit(Negation expression) { element("Negation", expression.operand);
        return null;
    }

    public String visit(Modulo expression) { element("Modulo", expression.left, expression.right);
        return null;
    }

    public String visit(Exponentiation expression) { element("Exponentiation", expression.base, expression.exponent);
        return null;
    }

    public String visit(Equality expression) { element("Equality", expression.left, expression.right);
        return null;
    }

    public String visit(Inequality expression) { element("Inequality", expression.left, expression.right);
        return null;
    }

    public String visit(LessThan expression) { element("LessThan", expression.left, expression.right);
        return null;
    }

    public String visit(GreaterThan expression) { element("GreaterThan", expression.left, expression.right);
        return null;
    }

    public String visit(LessThanOrEqual expression) { element("LessThanOrEqual", expression.left, expression.right);
        return null;
    }

    public String visit(GreaterThanOrEqual expression) { element("GreaterThanOrEqual", expression.left, expression.right);
        return null;
    }

    public String visit(Conjunction expression) { element("Conjunction", expression.left, expression.right);
        return null;
    }

    public String visit(Disjunction expression) { element("Disjunction", expression.left, expression.right);
        return null;
    }

    public String visit(LogicalNot expression) { element("LogicalNot", expression.operand);
        return null;
    }

    public String visit(Conditional expression) { element("Conditional", expression.condition, expression.whenTrue, expression.whenFalse);
        return null;
    }

    public String visit(FunctionCall expression) { builder.append(indent()).append("<FunctionCall>\n");
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

}