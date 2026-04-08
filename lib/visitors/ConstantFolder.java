package lib.visitors;

import java.util.function.Function;

import port.ICleanHandlerFactory;

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

