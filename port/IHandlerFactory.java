package port;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public interface IHandlerFactory<E> {

    <T, R> R handleState(StateConsumer<E, T, R> handler);

    IExpressionFactory<E> expressionFactory();

    Function<E, List<E>> expressionChildren();

    Function<E, IExpressionDict<Integer>> histogram();

    Function<E, E> renameVariable(String oldName, String newName);

    Function<E, String> expressionClassNameExtractor();

    Function<E, E> constantFolder();

    Function<E, E> constantFolderOnce();

    Function<E, E> expressionMapper(BiFunction<E, Supplier<E>, E> recurse);

    Function<E, BindingPower> createBindingPowerHandler();

    Function<E, String> cLikeSyntaxPrinter();

    Function<E, String> lispLikeSyntaxPrinter();

    Function<E, Integer> integerEvaluator(Map<String, Integer> variables,
            Map<String, Function<List<Integer>, Integer>> functions);
}