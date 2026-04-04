package spec.handlers;

import lib.expression.Expression;
import port.IExpressionFactory;

final class ExpressionFactoryAdapter<E extends Expression> implements IExpressionFactory<E> {
    private final IExpressionFactory<Expression> delegate;

    ExpressionFactoryAdapter(IExpressionFactory<Expression> delegate) {
        this.delegate = delegate;
    }

    public E literal(String value) {
        return cast(delegate.literal(value));
    }

    public E variableReference(String name) {
        return cast(delegate.variableReference(name));
    }

    public E addition(E left, E right) {
        return cast(delegate.addition(left, right));
    }

    public E subtraction(E left, E right) {
        return cast(delegate.subtraction(left, right));
    }

    public E multiplication(E left, E right) {
        return cast(delegate.multiplication(left, right));
    }

    public E division(E dividend, E divisor) {
        return cast(delegate.division(dividend, divisor));
    }

    public E negation(E operand) {
        return cast(delegate.negation(operand));
    }

    public E modulo(E left, E right) {
        return cast(delegate.modulo(left, right));
    }

    public E exponentiation(E base, E exponent) {
        return cast(delegate.exponentiation(base, exponent));
    }

    public E equality(E left, E right) {
        return cast(delegate.equality(left, right));
    }

    public E inequality(E left, E right) {
        return cast(delegate.inequality(left, right));
    }

    public E lessThan(E left, E right) {
        return cast(delegate.lessThan(left, right));
    }

    public E greaterThan(E left, E right) {
        return cast(delegate.greaterThan(left, right));
    }

    public E lessThanOrEqual(E left, E right) {
        return cast(delegate.lessThanOrEqual(left, right));
    }

    public E greaterThanOrEqual(E left, E right) {
        return cast(delegate.greaterThanOrEqual(left, right));
    }

    public E conjunction(E left, E right) {
        return cast(delegate.conjunction(left, right));
    }

    public E disjunction(E left, E right) {
        return cast(delegate.disjunction(left, right));
    }

    public E logicalNot(E operand) {
        return cast(delegate.logicalNot(operand));
    }

    public E conditional(E condition, E whenTrue, E whenFalse) {
        return cast(delegate.conditional(condition, whenTrue, whenFalse));
    }

    @SafeVarargs
    public final E functionCall(E callee, E... arguments) {
        var adaptedArguments = new Expression[arguments.length];
        for (int index = 0; index < arguments.length; index++) {
            adaptedArguments[index] = arguments[index];
        }
        return cast(delegate.functionCall(callee, adaptedArguments));
    }

    @SuppressWarnings("unchecked")
    private E cast(Expression expression) {
        return (E) expression;
    }
}
