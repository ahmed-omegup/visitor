package lib.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public class ExpressionChildren<E> implements EExpressionVisitor<List<E>, E> {

    public List<E> visit(ELiteral<E> expression) { return List.of(); }
    public List<E> visit(EVariableReference<E> expression) { return List.of(); }
    public List<E> visit(EAddition<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(ESubtraction<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EMultiplication<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EDivision<E> expression) { return List.of(expression.dividend, expression.divisor); }
    public List<E> visit(ENegation<E> expression) { return List.of(expression.operand); }
    public List<E> visit(EModulo<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EExponentiation<E> expression) { return List.of(expression.base, expression.exponent); }
    public List<E> visit(EEquality<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EInequality<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(ELessThan<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EGreaterThan<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(ELessThanOrEqual<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EGreaterThanOrEqual<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EConjunction<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(EDisjunction<E> expression) { return List.of(expression.left, expression.right); }
    public List<E> visit(ELogicalNot<E> expression) { return List.of(expression.operand); }
    public List<E> visit(EConditional<E> expression) { return List.of(expression.condition, expression.whenTrue, expression.whenFalse); }

    public List<E> visit(EFunctionCall<E> expression) {
        var children = new ArrayList<E>(expression.arguments.size() + 1);
        children.add(expression.callee);
        children.addAll(expression.arguments);
        return children;
    }
}