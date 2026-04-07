package lib.visitors;

import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public final class ExpressionStringifier implements Visitor<String> {
    @FunctionalInterface
    public interface Renderer {
        String render(Expression expression, Function<Expression, String> stringify, Function<Expression, Integer> priority);
    }

    private final Function<Expression, Integer> priorities;
    private final Renderer renderer;

    public ExpressionStringifier(Function<Expression, Integer> priorities, Renderer renderer) {
        this.priorities = priorities;
        this.renderer = renderer;
    }

    public static String renderChild(
        Expression child,
        int parentPriority,
        Function<Expression, String> stringify,
        Function<Expression, Integer> priority
    ) {
        var rendered = stringify.apply(child);
        if (priority.apply(child) < parentPriority) {
            return "(" + rendered + ")";
        }
        return rendered;
    }

    public static String infix(
        Expression left,
        String operator,
        Expression right,
        int parentPriority,
        Function<Expression, String> stringify,
        Function<Expression, Integer> priority
    ) {
        return renderChild(left, parentPriority, stringify, priority)
            + " " + operator + " "
            + renderChild(right, parentPriority, stringify, priority);
    }

    public static String prefix(
        String operator,
        Expression operand,
        int parentPriority,
        Function<Expression, String> stringify,
        Function<Expression, Integer> priority
    ) {
        return operator + renderChild(operand, parentPriority, stringify, priority);
    }

    public static String call(Expression callee, List<Expression> arguments, Function<Expression, String> stringify) {
        return stringify.apply(callee)
            + "(" + arguments.stream().map(stringify).collect(java.util.stream.Collectors.joining(", ")) + ")";
    }

    private String render(Expression expression) {
        return renderer.render(expression, this, priorities);
    }

    public String visit(Literal expression) { return render(expression); }
    public String visit(VariableReference expression) { return render(expression); }
    public String visit(Addition expression) { return render(expression); }
    public String visit(Subtraction expression) { return render(expression); }
    public String visit(Multiplication expression) { return render(expression); }
    public String visit(Division expression) { return render(expression); }
    public String visit(Negation expression) { return render(expression); }
    public String visit(Modulo expression) { return render(expression); }
    public String visit(Exponentiation expression) { return render(expression); }
    public String visit(Equality expression) { return render(expression); }
    public String visit(Inequality expression) { return render(expression); }
    public String visit(LessThan expression) { return render(expression); }
    public String visit(GreaterThan expression) { return render(expression); }
    public String visit(LessThanOrEqual expression) { return render(expression); }
    public String visit(GreaterThanOrEqual expression) { return render(expression); }
    public String visit(Conjunction expression) { return render(expression); }
    public String visit(Disjunction expression) { return render(expression); }
    public String visit(LogicalNot expression) { return render(expression); }
    public String visit(Conditional expression) { return render(expression); }
    public String visit(FunctionCall expression) { return render(expression); }
}