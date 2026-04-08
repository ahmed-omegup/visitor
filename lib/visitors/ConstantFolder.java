package lib.visitors;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.*;
import lib.expression.category.*;
import lib.utils.Either;
import lib.utils.EitherVisitor;
import port.ICleanHandlerFactory;
import port.IExpressionFactory;

public class ConstantFolder<E> implements Function<E, E> {
    private final Function<E, E> mapper;

    public ConstantFolder(ICleanHandlerFactory<E> handlers) {
        var foldOnce = handlers.constantFolderOnce();
        mapper = handlers.expressionMapper((_, recurse) -> foldOnce.apply(recurse.get()));
    }

    @Override
    public E apply(E expression) {
        return mapper.apply(expression);
    }

}

