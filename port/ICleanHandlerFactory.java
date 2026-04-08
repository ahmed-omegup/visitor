package port;

import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.Literal;
import lib.expressions.Expressions;
import lib.utils.Either;

public interface ICleanHandlerFactory<E> {

    <T, R> R handleState(StateConsumer<E, T, R> handler);

    IExpressionFactory<E> expressionFactory();

    Function<E, Either<Literal<E>, E>> isLiteral();

    Function<E, Map<Integer, Integer>> arithmeticDepthHistogramBuilder();

    Function<E, List<E>> expressionChildren();

    Function<E, String> expressionClassNameExtractor();

    Function<E, E> constantFolder();

    Function<E, E> constantFolderOnce();

    Function<E, E> expressionMapper(BiFunction<E, Supplier<E>, E> recurse);

    Function<E, BindingPower> createBindingPowerHandler();

    Function<E, String> cLikeSyntaxPrinter();

    <T> Function<E, T> isomorphicGetter(Expressions<T> values);

    <T> Consumer<E> isomorphicSetter(Expressions<T> values, Function<E, T> reducer);

    <T> Function<E, T> globalReduceVisitor(Expressions<T> values, BinaryOperator<T> reducer);

    <T> Consumer<E> localReduceVisitor(Expressions<T> values, T initial, BiFunction<T, E, T> reducer);

    Function<E, String> lispLikeSyntaxPrinter();

    Function<E, Integer> integerEvaluator();

    Function<E, Integer> integerEvaluator(Map<String, Integer> variables,
            Map<String, Function<List<Integer>, Integer>> functions);
}