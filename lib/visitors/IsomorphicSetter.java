package lib.visitors;

import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;
import lib.expressions.Expressions;

public final class IsomorphicSetter<T, E> implements Consumer<Expression<E>>, ExpressionVisitor<Void, E> {
    private final Expressions<T> values;
    private final Function<Expression<E>, T> handler;

    public IsomorphicSetter(Expressions<T> values, Function<Expression<E>, T> handler) {
        this.values = values;
        this.handler = handler;
    }

    public void accept(Expression<E> expression) {
        expression.accept(this);
    }

    public Void visit(Literal<E> expression) {
        values.literal = handler.apply(expression);
        return null;
    }

    public Void visit(VariableReference<E> expression) {
        values.variableReference = handler.apply(expression);
        return null;
    }

    public Void visit(Addition<E> expression) {
        values.addition = handler.apply(expression);
        return null;
    }

    public Void visit(Subtraction<E> expression) {
        values.subtraction = handler.apply(expression);
        return null;
    }

    public Void visit(Multiplication<E> expression) {
        values.multiplication = handler.apply(expression);
        return null;
    }

    public Void visit(Division<E> expression) {
        values.division = handler.apply(expression);
        return null;
    }

    public Void visit(Negation<E> expression) {
        values.negation = handler.apply(expression);
        return null;
    }

    public Void visit(Modulo<E> expression) {
        values.modulo = handler.apply(expression);
        return null;
    }

    public Void visit(Exponentiation<E> expression) {
        values.exponentiation = handler.apply(expression);
        return null;
    }

    public Void visit(Equality<E> expression) {
        values.equality = handler.apply(expression);
        return null;
    }

    public Void visit(Inequality<E> expression) {
        values.inequality = handler.apply(expression);
        return null;
    }

    public Void visit(LessThan<E> expression) {
        values.lessThan = handler.apply(expression);
        return null;
    }

    public Void visit(GreaterThan<E> expression) {
        values.greaterThan = handler.apply(expression);
        return null;
    }

    public Void visit(LessThanOrEqual<E> expression) {
        values.lessThanOrEqual = handler.apply(expression);
        return null;
    }

    public Void visit(GreaterThanOrEqual<E> expression) {
        values.greaterThanOrEqual = handler.apply(expression);
        return null;
    }

    public Void visit(Conjunction<E> expression) {
        values.conjunction = handler.apply(expression);
        return null;
    }

    public Void visit(Disjunction<E> expression) {
        values.disjunction = handler.apply(expression);
        return null;
    }

    public Void visit(LogicalNot<E> expression) {
        values.logicalNot = handler.apply(expression);
        return null;
    }

    public Void visit(Conditional<E> expression) {
        values.conditional = handler.apply(expression);
        return null;
    }

    public Void visit(FunctionCall<E> expression) {
        values.functionCall = handler.apply(expression);
        return null;
    }
}