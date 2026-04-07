package lib.visitors;

import java.util.function.Function;
import java.util.stream.Collectors;

import lib.expression.*;

public final class ExpressionToCLikeSyntax implements Visitor<String> {
    private final Function<Expression, BindingPower> bindingPowers;

    public ExpressionToCLikeSyntax() {
        this(new IsomorphicGetter<>(new CLikeBindingPowers())::apply);
    }

    public ExpressionToCLikeSyntax(Function<Expression, BindingPower> bindingPowers) {
        this.bindingPowers = bindingPowers;
    }

    private String renderChild(Expression child, BindingPower parentBinding, boolean isRightChild) {
        var rendered = apply(child);
        var childBinding = bindingPower(child);
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

    private String infix(Expression left, String operator, Expression right, BindingPower parentBinding) {
        return renderChild(left, parentBinding, false)
            + " " + operator + " "
            + renderChild(right, parentBinding, true);
    }

    private String prefix(String operator, Expression operand, BindingPower parentBinding) {
        return operator + renderChild(operand, parentBinding, true);
    }

    private String call(FunctionCall expression) {
        return renderChild(expression.callee, bindingPower(expression), false)
            + "(" + expression.arguments.stream().map(this).collect(Collectors.joining(", ")) + ")";
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
        return infix(expression.left, "+", expression.right, bindingPower(expression));
    }

    public String visit(Subtraction expression) {
        return infix(expression.left, "-", expression.right, bindingPower(expression));
    }

    public String visit(Multiplication expression) {
        return infix(expression.left, "*", expression.right, bindingPower(expression));
    }

    public String visit(Division expression) {
        return infix(expression.dividend, "/", expression.divisor, bindingPower(expression));
    }

    public String visit(Negation expression) {
        return prefix("-", expression.operand, bindingPower(expression));
    }

    public String visit(Modulo expression) {
        return infix(expression.left, "%", expression.right, bindingPower(expression));
    }

    public String visit(Exponentiation expression) {
        return "pow(" + apply(expression.base) + ", " + apply(expression.exponent) + ")";
    }

    public String visit(Equality expression) {
        return infix(expression.left, "==", expression.right, bindingPower(expression));
    }

    public String visit(Inequality expression) {
        return infix(expression.left, "!=", expression.right, bindingPower(expression));
    }

    public String visit(LessThan expression) {
        return infix(expression.left, "<", expression.right, bindingPower(expression));
    }

    public String visit(GreaterThan expression) {
        return infix(expression.left, ">", expression.right, bindingPower(expression));
    }

    public String visit(LessThanOrEqual expression) {
        return infix(expression.left, "<=", expression.right, bindingPower(expression));
    }

    public String visit(GreaterThanOrEqual expression) {
        return infix(expression.left, ">=", expression.right, bindingPower(expression));
    }

    public String visit(Conjunction expression) {
        return infix(expression.left, "&&", expression.right, bindingPower(expression));
    }

    public String visit(Disjunction expression) {
        return infix(expression.left, "||", expression.right, bindingPower(expression));
    }

    public String visit(LogicalNot expression) {
        return prefix("!", expression.operand, bindingPower(expression));
    }

    public String visit(Conditional expression) {
        var binding = bindingPower(expression);
        return renderChild(expression.condition, binding, false)
            + " ? " + apply(expression.whenTrue)
            + " : " + renderChild(expression.whenFalse, binding, true);
    }

    public String visit(FunctionCall expression) {
        return call(expression);
    }
}