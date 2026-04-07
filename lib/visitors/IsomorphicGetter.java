package lib.visitors;

import java.util.function.Function;

import lib.expression.*;

public final class IsomorphicGetter<T> implements Visitor<T> {
    private final Expressions<T> values;

    public IsomorphicGetter(Expressions<T> values) {
        this.values = values;
    }

    public T apply(Expression expression) {
        return expression.accept(this);
    }

    public T visit(Literal expression) { return values.literal; }
    public T visit(VariableReference expression) { return values.variableReference; }
    public T visit(Addition expression) { return values.addition; }
    public T visit(Subtraction expression) { return values.subtraction; }
    public T visit(Multiplication expression) { return values.multiplication; }
    public T visit(Division expression) { return values.division; }
    public T visit(Negation expression) { return values.negation; }
    public T visit(Modulo expression) { return values.modulo; }
    public T visit(Exponentiation expression) { return values.exponentiation; }
    public T visit(Equality expression) { return values.equality; }
    public T visit(Inequality expression) { return values.inequality; }
    public T visit(LessThan expression) { return values.lessThan; }
    public T visit(GreaterThan expression) { return values.greaterThan; }
    public T visit(LessThanOrEqual expression) { return values.lessThanOrEqual; }
    public T visit(GreaterThanOrEqual expression) { return values.greaterThanOrEqual; }
    public T visit(Conjunction expression) { return values.conjunction; }
    public T visit(Disjunction expression) { return values.disjunction; }
    public T visit(LogicalNot expression) { return values.logicalNot; }
    public T visit(Conditional expression) { return values.conditional; }
    public T visit(FunctionCall expression) { return values.functionCall; }
}