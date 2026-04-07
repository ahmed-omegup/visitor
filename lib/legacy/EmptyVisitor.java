package lib.legacy;

import lib.expression.*;

abstract class EmptyVisitor<T> implements ExpressionVisitor<T> {
    public T visit(Literal expression) { return null; }
    public T visit(VariableReference expression) { return null; }
    public T visit(Addition expression) { return null; }
    public T visit(Subtraction expression) { return null; }
    public T visit(Multiplication expression) { return null; }
    public T visit(Division expression) { return null; }
    public T visit(Negation expression) { return null; }
    public T visit(Modulo expression) { return null; }
    public T visit(Exponentiation expression) { return null; }
    public T visit(Equality expression) { return null; }
    public T visit(Inequality expression) { return null; }
    public T visit(LessThan expression) { return null; }
    public T visit(GreaterThan expression) { return null; }
    public T visit(LessThanOrEqual expression) { return null; }
    public T visit(GreaterThanOrEqual expression) { return null; }
    public T visit(Conjunction expression) { return null; }
    public T visit(Disjunction expression) { return null; }
    public T visit(LogicalNot expression) { return null; }
    public T visit(Conditional expression) { return null; }
    public T visit(FunctionCall expression) { return null; }
}