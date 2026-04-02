package lib.expression;

public final class Factory implements port.IFactory {
    public Factory() {}

    public Literal literal(String value) {
        return new Literal(value);
    }

    public VariableReference variableReference(String name) {
        return new VariableReference(name);
    }

    public Addition addition(Expression left, Expression right) {
        return new Addition(left, right);
    }

    public Subtraction subtraction(Expression left, Expression right) {
        return new Subtraction(left, right);
    }

    public Multiplication multiplication(Expression left, Expression right) {
        return new Multiplication(left, right);
    }

    public Division division(Expression dividend, Expression divisor) {
        return new Division(dividend, divisor);
    }

    public Negation negation(Expression operand) {
        return new Negation(operand);
    }

    public Modulo modulo(Expression left, Expression right) {
        return new Modulo(left, right);
    }

    public Exponentiation exponentiation(Expression base, Expression exponent) {
        return new Exponentiation(base, exponent);
    }

    public Equality equality(Expression left, Expression right) {
        return new Equality(left, right);
    }

    public Inequality inequality(Expression left, Expression right) {
        return new Inequality(left, right);
    }

    public LessThan lessThan(Expression left, Expression right) {
        return new LessThan(left, right);
    }

    public GreaterThan greaterThan(Expression left, Expression right) {
        return new GreaterThan(left, right);
    }

    public LessThanOrEqual lessThanOrEqual(Expression left, Expression right) {
        return new LessThanOrEqual(left, right);
    }

    public GreaterThanOrEqual greaterThanOrEqual(Expression left, Expression right) {
        return new GreaterThanOrEqual(left, right);
    }

    public Conjunction conjunction(Expression left, Expression right) {
        return new Conjunction(left, right);
    }

    public Disjunction disjunction(Expression left, Expression right) {
        return new Disjunction(left, right);
    }

    public LogicalNot logicalNot(Expression operand) {
        return new LogicalNot(operand);
    }

    public Conditional conditional(Expression condition, Expression whenTrue, Expression whenFalse) {
        return new Conditional(condition, whenTrue, whenFalse);
    }

    public FunctionCall functionCall(Expression callee, Expression... arguments) {
        return new FunctionCall(callee, arguments);
    }
}