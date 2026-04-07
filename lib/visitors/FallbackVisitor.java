package lib.visitors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public abstract class FallbackVisitor<R> implements ExpressionVisitor<R> {
    private final Function<Expression, R> fallback;

    public FallbackVisitor(Function<Expression, R> fallback) {
        this.fallback = fallback;
    }

    private R empty(Expression e) {
        return fallback.apply(e);
    }

    public R visit(Literal e) {
        return empty(e);
    }
    public R visit(VariableReference e) {
        return empty(e);
    }
    public R visit(Addition e) {
        return empty(e);
    }
    public R visit(Subtraction e) {
        return empty(e);
    }
    public R visit(Multiplication e) {
        return empty(e);
    }
    public R visit(Division e) {
        return empty(e);
    }
    public R visit(Negation e) {
        return empty(e);
    }
    public R visit(Modulo e) {
        return empty(e);
    }
    public R visit(Exponentiation e) {
        return empty(e);
    }
    public R visit(Equality e) {
        return empty(e);
    }
    public R visit(Inequality e) {
        return empty(e);
    }
    public R visit(LessThan e) {
        return empty(e);
    }
    public R visit(GreaterThan e) {
        return empty(e);
    }
    public R visit(LessThanOrEqual e) {
        return empty(e);
    }
    public R visit(GreaterThanOrEqual e) {
        return empty(e);
    }
    public R visit(Conjunction e) {
        return empty(e);
    }
    public R visit(Disjunction e) {
        return empty(e);
    }
    public R visit(LogicalNot e) {
        return empty(e);
    }
    public R visit(Conditional e) {
        return empty(e);
    }
    public R visit(FunctionCall e) {
        return empty(e);
    }
    
}