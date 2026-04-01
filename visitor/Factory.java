package visitor;

import visitor.expression.*;

public class Factory {
    static Literal literal(String value) { return new Literal(value); }
    static VariableReference variableReference(String name) { return new VariableReference(name); }
    static Addition addition(Expression left, Expression right) { return new Addition(left, right); }
    static Subtraction subtraction(Expression left, Expression right) { return new Subtraction(left, right); }
    static Multiplication multiplication(Expression left, Expression right) { return new Multiplication(left, right); }
    static Division division(Expression dividend, Expression divisor) { return new Division(dividend, divisor); }
    static Negation negation(Expression operand) { return new Negation(operand); }
    static Modulo modulo(Expression left, Expression right) { return new Modulo(left, right); }
    static Exponentiation exponentiation(Expression base, Expression exponent) { return new Exponentiation(base, exponent); }
    static Equality equality(Expression left, Expression right) { return new Equality(left, right); }
    static Inequality inequality(Expression left, Expression right) { return new Inequality(left, right); }
    static LessThan lessThan(Expression left, Expression right) { return new LessThan(left, right); }
    static GreaterThan greaterThan(Expression left, Expression right) { return new GreaterThan(left, right); }
    static LessThanOrEqual lessThanOrEqual(Expression left, Expression right) { return new LessThanOrEqual(left, right); }
    static GreaterThanOrEqual greaterThanOrEqual(Expression left, Expression right) { return new GreaterThanOrEqual(left, right); }
    static Conjunction conjunction(Expression left, Expression right) { return new Conjunction(left, right); }
    static Disjunction disjunction(Expression left, Expression right) { return new Disjunction(left, right); }
    static LogicalNot logicalNot(Expression operand) { return new LogicalNot(operand); }
    static Conditional conditional(Expression condition, Expression whenTrue, Expression whenFalse) { return new Conditional(condition, whenTrue, whenFalse); }
    static FunctionCall functionCall(Expression callee, Expression... arguments) { return new FunctionCall(callee, arguments); }
}
