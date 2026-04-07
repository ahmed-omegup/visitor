package lib.visitors;

import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public final class ExpressionToCLikeSyntax implements Visitor<String> {
    private final Function<Expression, Integer> priorities;

    public ExpressionToCLikeSyntax(Function<Expression, Integer> priorities) {
        this.priorities = priorities;
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

    private int priority(Expression expression) {
        return priorities.apply(expression);
    }

    public String visit(Literal expression) {
        return expression.value;
    }

    public String visit(VariableReference expression) {
        return expression.name;
    }

    public String visit(Addition expression) {
        return infix(expression.left, "+", expression.right, priority(expression), this, priorities);
    }

    public String visit(Subtraction expression) {
        return infix(expression.left, "-", expression.right, priority(expression), this, priorities);
    }

    public String visit(Multiplication expression) {
        return infix(expression.left, "*", expression.right, priority(expression), this, priorities);
    }

    public String visit(Division expression) {
        return infix(expression.dividend, "/", expression.divisor, priority(expression), this, priorities);
    }

    public String visit(Negation expression) {
        return prefix("-", expression.operand, priority(expression), this, priorities);
    }

    public String visit(Modulo expression) {
        return infix(expression.left, "%", expression.right, priority(expression), this, priorities);
    }

    public String visit(Exponentiation expression) {
        return "pow(" + apply(expression.base) + ", " + apply(expression.exponent) + ")";
    }

    public String visit(Equality expression) {
        return infix(expression.left, "==", expression.right, priority(expression), this, priorities);
    }

    public String visit(Inequality expression) {
        return infix(expression.left, "!=", expression.right, priority(expression), this, priorities);
    }

    public String visit(LessThan expression) {
        return infix(expression.left, "<", expression.right, priority(expression), this, priorities);
    }

    public String visit(GreaterThan expression) {
        return infix(expression.left, ">", expression.right, priority(expression), this, priorities);
    }

    public String visit(LessThanOrEqual expression) {
        return infix(expression.left, "<=", expression.right, priority(expression), this, priorities);
    }

    public String visit(GreaterThanOrEqual expression) {
        return infix(expression.left, ">=", expression.right, priority(expression), this, priorities);
    }

    public String visit(Conjunction expression) {
        return infix(expression.left, "&&", expression.right, priority(expression), this, priorities);
    }

    public String visit(Disjunction expression) {
        return infix(expression.left, "||", expression.right, priority(expression), this, priorities);
    }

    public String visit(LogicalNot expression) {
        return prefix("!", expression.operand, priority(expression), this, priorities);
    }

    public String visit(Conditional expression) {
        return infix(expression.condition, "?", expression.whenTrue, priority(expression), this, priorities)
            + " : " + renderChild(expression.whenFalse, priority(expression), this, priorities);
    }

    public String visit(FunctionCall expression) {
        return call(expression.callee, expression.arguments, this);
    }
}