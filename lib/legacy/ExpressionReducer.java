package lib.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.BiFunction;
import java.util.function.Function;

import lib.expression.*;

class ExpressionChildren implements Function<Expression, Iterable<Expression>>, ExpressionVisitor<Iterable<Expression>> {
    public Iterable<Expression> apply(Expression expression) {
        return expression.accept(this);
    }

    public Iterable<Expression> visit(Literal expression) { return List.of(); }
    public Iterable<Expression> visit(VariableReference expression) { return List.of(); }
    public Iterable<Expression> visit(Addition expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(Subtraction expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(Multiplication expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(Division expression) { return List.of(expression.dividend, expression.divisor); }
    public Iterable<Expression> visit(Negation expression) { return List.of(expression.operand); }
    public Iterable<Expression> visit(Modulo expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(Exponentiation expression) { return List.of(expression.base, expression.exponent); }
    public Iterable<Expression> visit(Equality expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(Inequality expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(LessThan expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(GreaterThan expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(LessThanOrEqual expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(GreaterThanOrEqual expression) { return List.of(expression.left, expression.right); } 
    public Iterable<Expression> visit(Conjunction expression) { return List.of(expression.left, expression.right); }    
    public Iterable<Expression> visit(Disjunction expression) { return List.of(expression.left, expression.right); }
    public Iterable<Expression> visit(LogicalNot expression) { return List.of(expression.operand); }
    public Iterable<Expression> visit(Conditional expression) { return List.of(expression.condition, expression.whenTrue, expression.whenFalse); }
    public Iterable<Expression> visit(FunctionCall expression) {
        var children = new Expression[expression.arguments.size() + 1];
        children[0] = expression.callee;
        for (int index = 0; index < expression.arguments.size(); index++) {
            children[index + 1] = expression.arguments.get(index);
        }
        return Arrays.asList(children);
    }

}


class ExpressionAccumulator<A, R> implements Function<Expression, R> {
    private final Function<Expression, A> handler;
    private final BiFunction<A, R, A> accumulator;
    private final Function<A, R> resultMapper;
    
    public ExpressionAccumulator(Function<Expression, A> handler, BiFunction<A, R, A> accumulator, Function<A, R> resultMapper) {
        this.handler = handler;
        this.accumulator = accumulator;
        this.resultMapper = resultMapper;
    }

    public R apply(Expression expression) {
        var result = handler.apply(expression);
        for (var child : new ExpressionChildren().apply(expression)) {
            result = accumulator.apply(result, apply(child));
        }
        return resultMapper.apply(result);
    }
}

public class ExpressionReducer<R> extends ExpressionAccumulator<R, R> {

    public ExpressionReducer(Function<Expression, R> handler, BinaryOperator<R> reducer) {
        super(handler, reducer, Function.identity());
    }

}