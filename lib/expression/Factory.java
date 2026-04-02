package lib.expression;

public final class Factory {
    private Factory() {}

    public static Literal literal(String value) {
        return new Literal(value);
    }

    public static VariableReference variableReference(String name) {
        return new VariableReference(name);
    }

    public static Addition addition(Expression left, Expression right) {
        return new Addition(left, right);
    }

    public static Subtraction subtraction(Expression left, Expression right) {
        return new Subtraction(left, right);
    }

    public static Multiplication multiplication(Expression left, Expression right) {
        return new Multiplication(left, right);
    }

    public static Division division(Expression dividend, Expression divisor) {
        return new Division(dividend, divisor);
    }

    public static Negation negation(Expression operand) {
        return new Negation(operand);
    }

    public static Modulo modulo(Expression left, Expression right) {
        return new Modulo(left, right);
    }

    public static Exponentiation exponentiation(Expression base, Expression exponent) {
        return new Exponentiation(base, exponent);
    }

    public static Equality equality(Expression left, Expression right) {
        return new Equality(left, right);
    }

    public static Inequality inequality(Expression left, Expression right) {
        return new Inequality(left, right);
    }

    public static LessThan lessThan(Expression left, Expression right) {
        return new LessThan(left, right);
    }

    public static GreaterThan greaterThan(Expression left, Expression right) {
        return new GreaterThan(left, right);
    }

    public static LessThanOrEqual lessThanOrEqual(Expression left, Expression right) {
        return new LessThanOrEqual(left, right);
    }

    public static GreaterThanOrEqual greaterThanOrEqual(Expression left, Expression right) {
        return new GreaterThanOrEqual(left, right);
    }

    public static Conjunction conjunction(Expression left, Expression right) {
        return new Conjunction(left, right);
    }

    public static Disjunction disjunction(Expression left, Expression right) {
        return new Disjunction(left, right);
    }

    public static LogicalNot logicalNot(Expression operand) {
        return new LogicalNot(operand);
    }

    public static Conditional conditional(Expression condition, Expression whenTrue, Expression whenFalse) {
        return new Conditional(condition, whenTrue, whenFalse);
    }

    public static FunctionCall functionCall(Expression callee, Expression... arguments) {
        return new FunctionCall(callee, arguments);
    }
}