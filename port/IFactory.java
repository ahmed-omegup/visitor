package port;

import lib.expression.*;

public interface IFactory {
    Literal literal(String value);

    VariableReference variableReference(String name);

    Addition addition(Expression left, Expression right);

    Subtraction subtraction(Expression left, Expression right);

    Multiplication multiplication(Expression left, Expression right);

    Division division(Expression dividend, Expression divisor);

    Negation negation(Expression operand);

    Modulo modulo(Expression left, Expression right);

    Exponentiation exponentiation(Expression base, Expression exponent);

    Equality equality(Expression left, Expression right);

    Inequality inequality(Expression left, Expression right);

    LessThan lessThan(Expression left, Expression right);

    GreaterThan greaterThan(Expression left, Expression right);

    LessThanOrEqual lessThanOrEqual(Expression left, Expression right);

    GreaterThanOrEqual greaterThanOrEqual(Expression left, Expression right);

    Conjunction conjunction(Expression left, Expression right);

    Disjunction disjunction(Expression left, Expression right);

    LogicalNot logicalNot(Expression operand);

    Conditional conditional(Expression condition, Expression whenTrue, Expression whenFalse);

    FunctionCall functionCall(Expression callee, Expression... arguments);
}