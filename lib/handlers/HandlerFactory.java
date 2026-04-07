package lib.handlers;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.Expression;
import lib.expression.Factory;
import lib.expressions.ExpressionClassNames;
import lib.expressions.Expressions;
import lib.visitors.ConstantFolder;
import lib.visitors.ExpressionChildren;
import lib.visitors.ExpressionMapper;
import lib.visitors.ExpressionToCLikeSyntax;
import lib.visitors.ExpressionToLispLikeSyntax;
import lib.visitors.IntegerEvaluationVisitor;
import lib.visitors.IsomorphicGetter;
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
}