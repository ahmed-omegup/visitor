package lib.handlers;

import java.util.List;
import java.util.Map;
import java.util.function.*;

import lib.dict.BindingPowersDict;
import lib.dict.ClassNamesDict;
import lib.dict.ConstDict;
import lib.dict.Dict;
import lib.expression.*;
import lib.utils.Either;
import lib.utils.Left;
import lib.utils.Right;
import lib.visitors.*;
import port.BindingPower;
import port.IHandlerFactory;
import port.IExpressionFactory;
import port.State;

public final class HandlerFactory implements IHandlerFactory<ExpressionV1> {
    @Override
    public IExpressionFactory<ExpressionV1> expressionFactory() {
        return new Factory();
    }

    public Function<ExpressionV1, Either<Literal<ExpressionV1>, ExpressionV1>> isLiteral() {
        return expression -> expression
                .accept(new FallbackVisitor<Either<Literal<ExpressionV1>, ExpressionV1>, ExpressionV1>(
                        _e -> new Right<>(expression)) {
                    public Either<Literal<ExpressionV1>, ExpressionV1> visit(Literal<ExpressionV1> e) {
                        return new Left<>(e);
                    }
                });
    };

    public Function<ExpressionV1, Either<VariableReference<ExpressionV1>, ExpressionV1>> isVariable() {
        return expression -> expression
                .accept(new FallbackVisitor<Either<VariableReference<ExpressionV1>, ExpressionV1>, ExpressionV1>(
                        _e -> new Right<>(expression)) {
                    public Either<VariableReference<ExpressionV1>, ExpressionV1> visit(
                            VariableReference<ExpressionV1> e) {
                        return new Left<>(e);
                    }
                });
    };

    public Function<ExpressionV1, ExpressionV1> constantFolderOnce() {
        return expression -> expression.accept(new ConstantFolderOnce<>(expressionFactory(), expression, isLiteral()));
    };

    public Function<ExpressionV1, BindingPower> createBindingPowerHandler() {
        var getter = new IsomorphicGetter<BindingPower, ExpressionV1>(new BindingPowersDict());
        return expression -> expression.accept(getter);
    };

    @Override
    public Function<ExpressionV1, List<ExpressionV1>> expressionChildren() {
        var children = new ExpressionChildren<ExpressionV1>();
        return expression -> expression.accept(children);
    }

    @Override
    public Function<ExpressionV1, String> expressionClassNameExtractor() {
        var classNameGetter = new IsomorphicGetter<String, ExpressionV1>(new ClassNamesDict());
        return expression -> expression.accept(classNameGetter);
    }

    @Override
    public Function<ExpressionV1, ExpressionV1> constantFolder() {
        return new ConstantFolder<>(this);
    }

    @Override
    public Function<ExpressionV1, ExpressionV1> expressionMapper(
            BiFunction<ExpressionV1, Supplier<ExpressionV1>, ExpressionV1> recurse) {
        var mapper = new ExpressionMapper<ExpressionV1>(this, recurse, (e, visitor) -> e.accept(visitor));
        return expression -> expression.accept(mapper);
    }

    @Override
    public Function<ExpressionV1, String> cLikeSyntaxPrinter() {
        return expression -> expression
                .accept(new ExpressionToCLikeSyntax<>(this, this.cLikeSyntaxPrinter(), expression));
    }

    @Override
    public Function<ExpressionV1, String> lispLikeSyntaxPrinter() {
        var visitor = new ExpressionToLispLikeSyntax<>(this);
        return expression -> expression.accept(visitor);
    }

    @Override
    public Function<ExpressionV1, Integer> integerEvaluator(Map<String, Integer> variables,
            Map<String, Function<List<Integer>, Integer>> functions) {
        var integerEvaluator = new IntegerEvaluationVisitor<>(variables, functions, isVariable(),
                (expression, visitor) -> expression.accept(visitor));
        return expression -> expression.accept(integerEvaluator);
    }

    public <T> Function<ExpressionV1, T> getter(Dict<T> values) {
        var visitor = new IsomorphicGetter<T, ExpressionV1>(values);
        return expression -> expression.accept(visitor);
    }

    public <T> Function<ExpressionV1, T> globalReduceVisitor(Dict<T> values, BinaryOperator<T> reducer) {
        return new GlobalReduceVisitor<T, ExpressionV1>(this, this::getter, values, reducer, this.expressionChildren());
    }

    public <T> State<ExpressionV1, Dict<T>, T> state() {
        return new State<ExpressionV1, Dict<T>, T>() {
            public Dict<T> intial(T value) {
                return new ConstDict<T>(value);
            };
            public Function<ExpressionV1,T> getter(Dict<T> state) {
                var visitor = new IsomorphicGetter<T, ExpressionV1>(state);
                return expression -> expression.accept(visitor);
            };
            public Consumer<ExpressionV1> setter(Dict<T> state, T value) {
                var visitor = new IsomorphicSetter<T, ExpressionV1>(state, value);
                return expression -> expression.accept(visitor);
            };
        };
    }

    public <T, R> R handleState(port.StateConsumer<ExpressionV1, T, R> handler) {
        return handler.consume(this.state());
    };

    public <T> Function<ExpressionV1, Dict<T>> localReduceVisitor(T initial, BiFunction<T, ExpressionV1, T> reducer) {

        return new LocalReduceVisitor<ExpressionV1, Dict<T>, T>(state(), initial, reducer, this.expressionChildren());
    }
}