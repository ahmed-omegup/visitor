package lib.visitors;

import java.util.stream.Collectors;

import lib.expression.*;

public final class ExpressionToLispLikeSyntax implements Visitor<String> {
    private String unary(String operator, Expression operand) {
        return "(" + operator + " " + apply(operand) + ")";
    }

    private String binary(String operator, Expression left, Expression right) {
        return "(" + operator + " " + apply(left) + " " + apply(right) + ")";
    }

    private String ternary(String operator, Expression first, Expression second, Expression third) {
        return "(" + operator + " " + apply(first) + " " + apply(second) + " " + apply(third) + ")";
    }

    public String visit(Literal expression) {
        return expression.value;
    }

    public String visit(VariableReference expression) {
        return expression.name;
    }

    public String visit(Addition expression) {
        return binary("+", expression.left, expression.right);
    }

    public String visit(Subtraction expression) {
        return binary("-", expression.left, expression.right);
    }

    public String visit(Multiplication expression) {
        return binary("*", expression.left, expression.right);
    }

    public String visit(Division expression) {
        return binary("/", expression.dividend, expression.divisor);
    }

    public String visit(Negation expression) {
        return unary("neg", expression.operand);
    }

    public String visit(Modulo expression) {
        return binary("mod", expression.left, expression.right);
    }

    public String visit(Exponentiation expression) {
        return binary("pow", expression.base, expression.exponent);
    }

    public String visit(Equality expression) {
        return binary("=", expression.left, expression.right);
    }

    public String visit(Inequality expression) {
        return binary("!=", expression.left, expression.right);
    }

    public String visit(LessThan expression) {
        return binary("<", expression.left, expression.right);
    }

    public String visit(GreaterThan expression) {
        return binary(">", expression.left, expression.right);
    }

    public String visit(LessThanOrEqual expression) {
        return binary("<=", expression.left, expression.right);
    }

    public String visit(GreaterThanOrEqual expression) {
        return binary(">=", expression.left, expression.right);
    }

    public String visit(Conjunction expression) {
        return binary("and", expression.left, expression.right);
    }

    public String visit(Disjunction expression) {
        return binary("or", expression.left, expression.right);
    }

    public String visit(LogicalNot expression) {
        return unary("not", expression.operand);
    }

    public String visit(Conditional expression) {
        return ternary("if", expression.condition, expression.whenTrue, expression.whenFalse);
    }

    public String visit(FunctionCall expression) {
        return "(" + apply(expression.callee)
            + (expression.arguments.isEmpty() ? "" : " " + expression.arguments.stream().map(this).collect(Collectors.joining(" ")))
            + ")";
    }
}