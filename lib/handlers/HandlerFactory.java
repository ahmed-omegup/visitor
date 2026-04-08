package lib.handlers;

import java.util.List;
import java.util.Map;
import java.util.function.*;

import lib.expression.*;
import lib.expressions.ExpressionClassNames;
import lib.expressions.Expressions;
import lib.visitors.*;
import port.ICleanHandlerFactory;
import port.IExpressionFactory;

public final class HandlerFactory implements ICleanHandlerFactory<ExpressionV1> {
    public IExpressionFactory<ExpressionV1> expressionFactory() {
        return new Factory();
    }

    public Function<ExpressionV1, Map<Integer, Integer>> arithmeticDepthHistogramBuilder() {
        return new ArithmeticDepthHistogramBuilder();
    }

    public Function<ExpressionV1, List<ExpressionV1>> expressionChildren() {
        var children = new ExpressionChildren();
        return expression -> expression.accept(children);
    }

    public Function<ExpressionV1, String> expressionClassNameExtractor() {
        var classNameGetter = new IsomorphicGetter<String, ExpressionV1>(new ExpressionClassNames());
        return expression -> expression.accept(classNameGetter);
    }

    public Function<ExpressionV1, ExpressionV1> constantFolder() {
        return new ConstantFolder(expressionFactory());
    }

    public Function<ExpressionV1, ExpressionV1> expressionMapper(
            BiFunction<ExpressionV1, Supplier<ExpressionV1>, ExpressionV1> recurse) {
        return new ExpressionMapper<ExpressionV1>(expressionFactory(), (e, visitor) -> e.accept(visitor));
    }

    public Function<ExpressionV1, String> cLikeSyntaxPrinter() {
        return expression -> expression.accept(new ExpressionToCLikeSyntax(this, this.cLikeSyntaxPrinter(), expression));
    }

    public Function<ExpressionV1, String> lispLikeSyntaxPrinter() {
        return new ExpressionToLispLikeSyntax(this);
    }

    public Function<ExpressionV1, Integer> integerEvaluator() {
        return new IntegerEvaluationVisitor();
    }

    public Function<ExpressionV1, Integer> integerEvaluator(Map<String, Integer> variables,
            Map<String, Function<List<Integer>, Integer>> functions) {
        return new IntegerEvaluationVisitor(variables, functions);
    }

    public <T> Function<ExpressionV1, T> globalReduceVisitor(Expressions<T> values, BinaryOperator<T> reducer) {
        return new GlobalReduceVisitor<>(values, reducer);
    }

    public <T> Consumer<ExpressionV1> localReduceVisitor(Expressions<T> values, BiFunction<T, ExpressionV1, T> reducer) {
        return new LocalReduceVisitor<>(values, reducer);
    }

    public <T> Function<ExpressionV1, T> isomorphicGetter(Expressions<T> values) {
        return new IsomorphicGetter<T>(values);
    };

    public <T> java.util.function.Consumer<ExpressionV1> isomorphicSetter(Expressions<T> values,
            Function<ExpressionV1, T> reducer) {
        return new IsomorphicSetter<T>(values, reducer);
    }
}