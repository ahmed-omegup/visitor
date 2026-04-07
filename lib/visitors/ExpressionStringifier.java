package lib.visitors;

import java.util.List;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.Visitor1;

public class ExpressionStringifier implements Function<Expression, String>, Visitor1<String> {
    private final Function<Expression, Integer> priorities;

    public ExpressionStringifier(Function<Expression, Integer> priorities) {
        this.priorities = priorities;
    }

    public final String apply(Expression expression) {
        return expression.accept(this);
    }

    protected final int priority(Expression expression) {
        return priorities.apply(expression);
    }

    protected final String renderChild(Expression child, int parentPriority) {
        var rendered = apply(child);
        if (priority(child) < parentPriority) {
            return "(" + rendered + ")";
        }
        return rendered;
    }

    protected final String infix(Expression left, String operator, Expression right, int parentPriority) {
        return renderChild(left, parentPriority) + " " + operator + " " + renderChild(right, parentPriority);
    }

    protected final String prefix(String operator, Expression operand, int parentPriority) {
        return operator + renderChild(operand, parentPriority);
    }

    protected final String call(Expression callee, List<Expression> arguments) {
        return apply(callee) + "(" + arguments.stream().map(this::apply).collect(java.util.stream.Collectors.joining(", ")) + ")";
    }

    protected String unsupported(Expression expression) {
        throw new UnsupportedOperationException(expression.getClass().getSimpleName());
    }

    public String visit(lib.expression.Literal expression) { return unsupported(expression); }
    public String visit(lib.expression.VariableReference expression) { return unsupported(expression); }
    public String visit(lib.expression.Addition expression) { return unsupported(expression); }
    public String visit(lib.expression.Subtraction expression) { return unsupported(expression); }
    public String visit(lib.expression.Multiplication expression) { return unsupported(expression); }
    public String visit(lib.expression.Division expression) { return unsupported(expression); }
    public String visit(lib.expression.Negation expression) { return unsupported(expression); }
    public String visit(lib.expression.Modulo expression) { return unsupported(expression); }
    public String visit(lib.expression.Exponentiation expression) { return unsupported(expression); }
    public String visit(lib.expression.Equality expression) { return unsupported(expression); }
    public String visit(lib.expression.Inequality expression) { return unsupported(expression); }
    public String visit(lib.expression.LessThan expression) { return unsupported(expression); }
    public String visit(lib.expression.GreaterThan expression) { return unsupported(expression); }
    public String visit(lib.expression.LessThanOrEqual expression) { return unsupported(expression); }
    public String visit(lib.expression.GreaterThanOrEqual expression) { return unsupported(expression); }
    public String visit(lib.expression.Conjunction expression) { return unsupported(expression); }
    public String visit(lib.expression.Disjunction expression) { return unsupported(expression); }
    public String visit(lib.expression.LogicalNot expression) { return unsupported(expression); }
    public String visit(lib.expression.Conditional expression) { return unsupported(expression); }
    public String visit(lib.expression.FunctionCall expression) { return unsupported(expression); }
}