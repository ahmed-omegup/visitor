package port;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Exponentiation;
import lib.expression.Expression;
import lib.expression.FunctionCall;
import lib.expression.GreaterThan;
import lib.expression.GreaterThanOrEqual;
import lib.expression.Inequality;
import lib.expression.LessThan;
import lib.expression.LessThanOrEqual;
import lib.expression.Literal;
import lib.expression.LogicalNot;
import lib.expression.Modulo;
import lib.expression.Multiplication;
import lib.expression.Negation;
import lib.expression.Subtraction;
import lib.expression.VariableReference;

public interface ExpressionFactory {
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