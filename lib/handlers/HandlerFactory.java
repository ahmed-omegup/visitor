package lib.handlers;

import java.util.List;
import java.util.Map;
import java.util.function.*;

import lib.expression.*;
import lib.expressions.Expressions;
import lib.visitors.*;
import port.ICleanHandlerFactory;
import port.IExpressionFactory;

public final class HandlerFactory implements ICleanHandlerFactory<Expression> {
    public IExpressionFactory<Expression> expressionFactory() {
        return new Factory();
    }

    public Function<Expression, Map<Integer, Integer>> arithmeticDepthHistogramBuilder() {
        return new ArithmeticDepthHistogramBuilder();
    }

    public Function<Expression, List<Expression>> expressionChildren() {
        var children = new ExpressionChildren();
        return expression -> expression.accept(children);
    }

    public Function<Expression, String> expressionClassNameExtractor() {
        return new ExpressionClassNameExtractor();
    }

    public Function<Expression, Expression> constantFolder() {
        return new ConstantFolder(expressionFactory());
    }

    public Function<Expression, Expression> expressionMapper(
            BiFunction<Expression, Supplier<Expression>, Expression> recurse) {
        return new ExpressionMapper(expressionFactory(), recurse);
    }

    public Function<Expression, String> cLikeSyntaxPrinter() {
        return new ExpressionToCLikeSyntax();
    }

    public Function<Expression, String> lispLikeSyntaxPrinter() {
        return new ExpressionToLispLikeSyntax();
    }

    public Function<Expression, Integer> integerEvaluator() {
        return new IntegerEvaluationVisitor();
    }

    public Function<Expression, Integer> integerEvaluator(Map<String, Integer> variables,
            Map<String, Function<List<Integer>, Integer>> functions) {
        return new IntegerEvaluationVisitor(variables, functions);
    }

    public <T> Function<Expression,T> globalReduceVisitor(Expressions<T> values, BinaryOperator<T> reducer) {
        return new GlobalReduceVisitor<>(values, reducer);
    }


    public <T> Consumer<Expression> localReduceVisitor(Expressions<T> values, BiFunction<T,Expression,T> reducer) {
        return new LocalReduceVisitor<>(values, reducer);
    }

    public <T> Function<Expression,T> isomorphicGetter(Expressions<T> values) {
        return new IsomorphicGetter<T>(values);
    };

    public <T> java.util.function.Consumer<Expression> isomorphicSetter(Expressions<T> values, Function<Expression,T> reducer) {
        return new IsomorphicSetter<T>(values, reducer);
    }
}