package port;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.ExpressionV1;

public interface IHandlerFactory2<E> extends IHandlerFactory<E> {

    IExpressionFactory2<E> expressionFactory();

    Function<E, IExpressionDict2<Integer>> histogram2();
}