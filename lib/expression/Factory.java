package lib.expression;

import java.util.List;
import port.IExpressionFactory;

public class Factory implements IExpressionFactory<ExpressionV1> {
    public Factory() {}

    private ExpressionV1 wrap(Expression<ExpressionV1> expression) {
        return new ExpressionV1(expression);
    }
    public ExpressionV1 literal(String value) {
        return wrap(new Literal<>(value));
    }

    public ExpressionV1 variableReference(String name) {
        return wrap(new VariableReference<>(name));
    }

    public ExpressionV1 addition(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Addition<>(left, right));
    }

    public ExpressionV1 subtraction(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Subtraction<>(left, right));
    }

    public ExpressionV1 multiplication(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Multiplication<>(left, right));
    }

    public ExpressionV1 division(ExpressionV1 dividend, ExpressionV1 divisor) {
        return wrap(new Division<>(dividend, divisor));
    }

    public ExpressionV1 negation(ExpressionV1 operand) {
        return wrap(new Negation<>(operand));
    }

    public ExpressionV1 modulo(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Modulo<>(left, right));
    }

    public ExpressionV1 exponentiation(ExpressionV1 base, ExpressionV1 exponent) {
        return wrap(new Exponentiation<>(base, exponent));
    }

    public ExpressionV1 equality(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Equality<>(left, right));
    }

    public ExpressionV1 inequality(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Inequality<>(left, right));
    }

    public ExpressionV1 lessThan(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new LessThan<>(left, right));
    }

    public ExpressionV1 greaterThan(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new GreaterThan<>(left, right));
    }

    public ExpressionV1 lessThanOrEqual(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new LessThanOrEqual<>(left, right));
    }

    public ExpressionV1 greaterThanOrEqual(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new GreaterThanOrEqual<>(left, right));
    }

    public ExpressionV1 conjunction(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Conjunction<>(left, right));
    }

    public ExpressionV1 disjunction(ExpressionV1 left, ExpressionV1 right) {
        return wrap(new Disjunction<>(left, right));
    }

    public ExpressionV1 logicalNot(ExpressionV1 operand) {
        return wrap(new LogicalNot<>(operand));
    }

    public ExpressionV1 conditional(ExpressionV1 condition, ExpressionV1 whenTrue, ExpressionV1 whenFalse) {
        return wrap(new Conditional<>(condition, whenTrue, whenFalse));
    }

    public ExpressionV1 functionCall(ExpressionV1 callee, List<ExpressionV1> arguments) {
        return wrap(new FunctionCall<>(callee, arguments));
    }
}