package lib.visitors;

import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public final class ExpressionToCLikeSyntax implements Visitor<String> {
    public record BindingPower(int priority, boolean isRightAssociative) {
    }

    private final Function<Expression, BindingPower> bindingPowers;

    public ExpressionToCLikeSyntax(Function<Expression, BindingPower> bindingPowers) {
        this.bindingPowers = bindingPowers;
    }

    public static String renderChild(
        Expression child,
        BindingPower parentBinding,
        boolean isRightChild,
        Function<Expression, String> stringify,
        Function<Expression, BindingPower> bindingPower
    ) {
        var rendered = stringify.apply(child);
        var childBinding = bindingPower.apply(child);
        if (childBinding.priority() < parentBinding.priority()) {
            return "(" + rendered + ")";
        }
        if (childBinding.priority() == parentBinding.priority()) {
            var needsParentheses = isRightChild ? !parentBinding.isRightAssociative() : parentBinding.isRightAssociative();
            if (needsParentheses) {
                return "(" + rendered + ")";
            }
        }
        return rendered;
    }

    public static String infix(
        Expression left,
        String operator,
        Expression right,
        BindingPower parentBinding,
        Function<Expression, String> stringify,
        Function<Expression, BindingPower> bindingPower
    ) {
        return renderChild(left, parentBinding, false, stringify, bindingPower)
            + " " + operator + " "
            + renderChild(right, parentBinding, true, stringify, bindingPower);
    }

    public static String prefix(
        String operator,
        Expression operand,
        BindingPower parentBinding,
        Function<Expression, String> stringify,
        Function<Expression, BindingPower> bindingPower
    ) {
        return operator + renderChild(operand, parentBinding, true, stringify, bindingPower);
    }

    public static String call(
        Expression callee,
        List<Expression> arguments,
        BindingPower parentBinding,
        Function<Expression, String> stringify,
        Function<Expression, BindingPower> bindingPower
    ) {
        return renderChild(callee, parentBinding, false, stringify, bindingPower)
            + "(" + arguments.stream().map(stringify).collect(java.util.stream.Collectors.joining(", ")) + ")";
    }

    private BindingPower bindingPower(Expression expression) {
        return bindingPowers.apply(expression);
    }

    public String visit(Literal expression) {
        return expression.value;
    }

    public String visit(VariableReference expression) {
        return expression.name;
    }

    public String visit(Addition expression) {
        return infix(expression.left, "+", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Subtraction expression) {
        return infix(expression.left, "-", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Multiplication expression) {
        return infix(expression.left, "*", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Division expression) {
        return infix(expression.dividend, "/", expression.divisor, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Negation expression) {
        return prefix("-", expression.operand, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Modulo expression) {
        return infix(expression.left, "%", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Exponentiation expression) {
        return "pow(" + apply(expression.base) + ", " + apply(expression.exponent) + ")";
    }

    public String visit(Equality expression) {
        return infix(expression.left, "==", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Inequality expression) {
        return infix(expression.left, "!=", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(LessThan expression) {
        return infix(expression.left, "<", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(GreaterThan expression) {
        return infix(expression.left, ">", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(LessThanOrEqual expression) {
        return infix(expression.left, "<=", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(GreaterThanOrEqual expression) {
        return infix(expression.left, ">=", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Conjunction expression) {
        return infix(expression.left, "&&", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Disjunction expression) {
        return infix(expression.left, "||", expression.right, bindingPower(expression), this, bindingPowers);
    }

    public String visit(LogicalNot expression) {
        return prefix("!", expression.operand, bindingPower(expression), this, bindingPowers);
    }

    public String visit(Conditional expression) {
        var binding = bindingPower(expression);
        return renderChild(expression.condition, binding, false, this, bindingPowers)
            + " ? " + apply(expression.whenTrue)
            + " : " + renderChild(expression.whenFalse, binding, true, this, bindingPowers);
    }

    public String visit(FunctionCall expression) {
        return call(expression.callee, expression.arguments, bindingPower(expression), this, bindingPowers);
    }
}