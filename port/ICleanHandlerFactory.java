package port;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ICleanHandlerFactory<E> {
    IExpressionFactory<E> expressionFactory();

    Function<E, Map<Integer, Integer>> arithmeticDepthHistogramBuilder();

    Function<E, List<E>> expressionChildren();

    Function<E, String> expressionClassNameExtractor();

    Function<E, E> constantFolder();

    Function<E, E> expressionMapper(BiFunction<E, Supplier<E>, E> recurse);

    Function<E, String> cLikeSyntaxPrinter();

    Function<E, String> lispLikeSyntaxPrinter();

    Function<E, Integer> integerEvaluator();

    Function<E, Integer> integerEvaluator(Map<String, Integer> variables,
            Map<String, Function<List<Integer>, Integer>> functions);
}