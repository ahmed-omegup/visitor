package lib.legacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import lib.expression.Addition;
import lib.expression.Conditional;
import lib.expression.Conjunction;
import lib.expression.Disjunction;
import lib.expression.Division;
import lib.expression.Equality;
import lib.expression.Expression;
import lib.expression.Exponentiation;
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
import lib.expression.Visitor;
import port.IHandlerFactory.Tree;

public final class ExpressionTreeBuilder<T> extends AbstractExpressionFunction<Tree<T>> {
    private final Function<Expression, T> valueBuilder;

    ExpressionTreeBuilder(Function<Expression, T> valueBuilder) {
        this.valueBuilder = valueBuilder;
    }

    public Tree<T> visit(Literal expression) {
        return node(expression);
    }

    public Tree<T> visit(VariableReference expression) {
        return node(expression);
    }

    public Tree<T> visit(Addition expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(Subtraction expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(Multiplication expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(Division expression) {
        return node(expression, expression.dividend, expression.divisor);
    }

    public Tree<T> visit(Negation expression) {
        return node(expression, expression.operand);
    }

    public Tree<T> visit(Modulo expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(Exponentiation expression) {
        return node(expression, expression.base, expression.exponent);
    }

    public Tree<T> visit(Equality expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(Inequality expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(LessThan expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(GreaterThan expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(LessThanOrEqual expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(GreaterThanOrEqual expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(Conjunction expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(Disjunction expression) {
        return node(expression, expression.left, expression.right);
    }

    public Tree<T> visit(LogicalNot expression) {
        return node(expression, expression.operand);
    }

    public Tree<T> visit(Conditional expression) {
        return node(expression, expression.condition, expression.whenTrue, expression.whenFalse);
    }

    public Tree<T> visit(FunctionCall expression) {
        var children = new ArrayList<Expression>(expression.arguments.size() + 1);
        children.add(expression.callee);
        children.addAll(expression.arguments);
        return node(expression, children);
    }

    private Tree<T> node(Expression expression, Expression... children) {
        return node(expression, Arrays.asList(children));
    }

    private Tree<T> node(Expression expression, List<Expression> children) {
        return new Tree<>(
            valueBuilder.apply(expression),
            children.stream().map(this::apply).toList()
        );
    }
}