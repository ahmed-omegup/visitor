package lib.visitors;

import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;
import lib.expressions.Expressions;

public final class IsomorphicSetter<T> implements Consumer<Expression>, ExpressionVisitor<Void> {
    private final Expressions<T> values;
    private final Function<Expression, T> handler;

    public IsomorphicSetter(Expressions<T> values, Function<Expression, T> handler) {
        this.values = values;
        this.handler = handler;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(Literal expression) {
        values.literal = handler.apply(expression);
        return null;
    }

    public Void visit(VariableReference expression) {
        values.variableReference = handler.apply(expression);
        return null;
    }

    public Void visit(Addition expression) {
        values.addition = handler.apply(expression);
        return null;
    }

    public Void visit(Subtraction expression) {
        values.subtraction = handler.apply(expression);
        return null;
    }

    public Void visit(Multiplication expression) {
        values.multiplication = handler.apply(expression);
        return null;
    }

    public Void visit(Division expression) {
        values.division = handler.apply(expression);
        return null;
    }

    public Void visit(Negation expression) {
        values.negation = handler.apply(expression);
        return null;
    }

    public Void visit(Modulo expression) {
        values.modulo = handler.apply(expression);
        return null;
    }

    public Void visit(Exponentiation expression) {
        values.exponentiation = handler.apply(expression);
        return null;
    }

    public Void visit(Equality expression) {
        values.equality = handler.apply(expression);
        return null;
    }

    public Void visit(Inequality expression) {
        values.inequality = handler.apply(expression);
        return null;
    }

    public Void visit(LessThan expression) {
        values.lessThan = handler.apply(expression);
        return null;
    }

    public Void visit(GreaterThan expression) {
        values.greaterThan = handler.apply(expression);
        return null;
    }

    public Void visit(LessThanOrEqual expression) {
        values.lessThanOrEqual = handler.apply(expression);
        return null;
    }

    public Void visit(GreaterThanOrEqual expression) {
        values.greaterThanOrEqual = handler.apply(expression);
        return null;
    }

    public Void visit(Conjunction expression) {
        values.conjunction = handler.apply(expression);
        return null;
    }

    public Void visit(Disjunction expression) {
        values.disjunction = handler.apply(expression);
        return null;
    }

    public Void visit(LogicalNot expression) {
        values.logicalNot = handler.apply(expression);
        return null;
    }

    public Void visit(Conditional expression) {
        values.conditional = handler.apply(expression);
        return null;
    }

    public Void visit(FunctionCall expression) {
        values.functionCall = handler.apply(expression);
        return null;
    }
}