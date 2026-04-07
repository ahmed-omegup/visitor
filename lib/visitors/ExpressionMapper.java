package lib.visitors;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.*;
import port.IExpressionFactory;

public final class ExpressionMapper implements Visitor<Expression> {
    private final BiFunction<Expression, Supplier<Expression>, Expression> recurse;
    private final IExpressionFactory<Expression> factory;

    public ExpressionMapper(
            IExpressionFactory<Expression> factory,
            BiFunction<Expression, Supplier<Expression>, Expression> recurse) {
        this.factory = factory;
        this.recurse = recurse;
    }

    public Expression apply(Expression expression) {
        return recurse.apply(expression, () -> expression.accept(this));
    }

    private List<Expression> produceAll(List<Expression> expressions) {
        return expressions.stream().map(this::apply).toList();
    }

    public Expression visit(Literal expression) {
        return expression;
    }

    public Expression visit(VariableReference expression) {
        return expression;
    }

    public Expression visit(Addition expression) {
        return factory.addition(apply(expression.left), apply(expression.right));
    }

    public Expression visit(Subtraction expression) {
        return factory.subtraction(apply(expression.left), apply(expression.right));
    }

    public Expression visit(Multiplication expression) {
        return factory.multiplication(apply(expression.left), apply(expression.right));
    }

    public Expression visit(Division expression) {
        return factory.division(apply(expression.dividend), apply(expression.divisor));
    }

    public Expression visit(Negation expression) {
        return factory.negation(apply(expression.operand));
    }

    public Expression visit(Modulo expression) {
        return factory.modulo(apply(expression.left), apply(expression.right));
    }

    public Expression visit(Exponentiation expression) {
        return factory.exponentiation(apply(expression.base), apply(expression.exponent));
    }

    public Expression visit(Equality expression) {
        return factory.equality(apply(expression.left), apply(expression.right));
    }

    public Expression visit(Inequality expression) {
        return factory.inequality(apply(expression.left), apply(expression.right));
    }

    public Expression visit(LessThan expression) {
        return factory.lessThan(apply(expression.left), apply(expression.right));
    }

    public Expression visit(GreaterThan expression) {
        return factory.greaterThan(apply(expression.left), apply(expression.right));
    }

    public Expression visit(LessThanOrEqual expression) {
        return factory.lessThanOrEqual(apply(expression.left), apply(expression.right));
    }

    public Expression visit(GreaterThanOrEqual expression) {
        return factory.greaterThanOrEqual(apply(expression.left), apply(expression.right));
    }

    public Expression visit(Conjunction expression) {
        return factory.conjunction(apply(expression.left), apply(expression.right));
    }

    public Expression visit(Disjunction expression) {
        return factory.disjunction(apply(expression.left), apply(expression.right));
    }

    public Expression visit(LogicalNot expression) {
        return factory.logicalNot(apply(expression.operand));
    }

    public Expression visit(Conditional expression) {
        return factory.conditional(apply(expression.condition), apply(expression.whenTrue),
                apply(expression.whenFalse));
    }

    public Expression visit(FunctionCall expression) {
        return factory.functionCall(apply(expression.callee), produceAll(expression.arguments));
    }
}