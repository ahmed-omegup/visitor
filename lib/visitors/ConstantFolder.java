package lib.visitors;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.*;
import lib.expression.category.*;
import lib.utils.Either;
import lib.utils.EitherVisitor;
import port.IExpressionFactory;

public class ConstantFolder<E> implements Function<E, E> {
    private final IExpressionFactory<E> factory;
    private final Function<E, Either<Literal<E>, E>> isLiteral;

    public ConstantFolder(IExpressionFactory<E> factory, Function<E, Either<Literal<E>, E>> isLiteral) {
        this.factory = factory;
        this.isLiteral = isLiteral;
    }

    private E foldOnce(E expression) {
        return expression.accept(new CategoryConstantFolder<E>(factory, expression, isLiteral));
    }

    @Override
    public E apply(E expression) {
        ExpressionMapper<E> mapper = new ExpressionMapper<>(factory, (_, recurse) -> foldOnce(recurse.get()));
        return mapper.apply(expression);
    }

}

