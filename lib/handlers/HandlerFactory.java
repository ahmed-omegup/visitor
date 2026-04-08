package lib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.*;

import lib.dict.BindingPowersDict;
import lib.dict.ClassNamesDict;
import lib.dict.ConstDict;
import lib.dict.Dict;
import lib.dict.OperationNamesI18n;
import lib.expression.*;
import lib.utils.Either;
import lib.utils.EitherVisitor;
import lib.utils.Left;
import lib.utils.Right;
import lib.visitors.*;
import port.BindingPower;
import port.ConstantFolder;
import port.IExpressionDict;
import port.IHandlerFactory;
import port.IExpressionFactory;
import port.State;

public class HandlerFactory implements IHandlerFactory<ExpressionV1> {
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
        return dictGetter(new BindingPowersDict());
    };

    @Override
    public Function<ExpressionV1, List<ExpressionV1>> expressionChildren() {
        var children = new ExpressionChildren<ExpressionV1>();
        return expression -> expression.accept(children);
    }

    @Override
    public Function<ExpressionV1, Boolean> literalChecker() {
        return expression -> isLiteral().apply(expression)
                .accept(new EitherVisitor<Literal<ExpressionV1>, ExpressionV1, Boolean>() {
                    public Boolean left(Literal<ExpressionV1> left) {
                        return true;
                    }

                    public Boolean right(ExpressionV1 right) {
                        return false;
                    }
                });
    }

    @Override
    public Function<ExpressionV1, Boolean> variableChecker() {
        return expression -> isVariable().apply(expression)
                .accept(new EitherVisitor<VariableReference<ExpressionV1>, ExpressionV1, Boolean>() {
                    public Boolean left(VariableReference<ExpressionV1> left) {
                        return true;
                    }

                    public Boolean right(ExpressionV1 right) {
                        return false;
                    }
                });
    }

    @Override
    public Function<ExpressionV1, String> expressionClassNameExtractor() {
        return dictGetter(new ClassNamesDict());
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
    public Function<ExpressionV1, String> jsLikeSyntaxPrinter() {
        return expression -> expression
                .accept(new ExpressionToJsLikeSyntax<>(this, this.jsLikeSyntaxPrinter(), expression));
    }

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

    public <T> Function<ExpressionV1, T> dictGetter(Dict<T> values) {
        var visitor = new IsomorphicGetter<T, ExpressionV1>(values);
        return expression -> expression.accept(visitor);
    }

    public Function<ExpressionV1, List<String>> collectClassNamesVisitor() {
        var classNames = expressionClassNameExtractor();
        return new GlobalReduceVisitor<>(e -> new ArrayList<>(List.of(classNames.apply(e))), (a, b) -> {
            a.addAll(b);
            return a;
        }, this.expressionChildren());
    }

    public <T> State<ExpressionV1, Dict<T>, T> state() {
        return new State<ExpressionV1, Dict<T>, T>() {
            public Dict<T> intial(T value) {
                return new ConstDict<T>(value);
            };

            public Function<ExpressionV1, T> getter(Dict<T> state) {
                return dictGetter(state);
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

    @Override
    public Function<ExpressionV1, IExpressionDict<Integer>> histogram() {
        var visitor = localReduceVisitor(0, (n, _e) -> n + 1);
        return expression -> visitor.apply(expression);
    }

    @Override
    public Function<ExpressionV1, ExpressionV1> renameVariable(String oldName, String newName) {
        return new ExpressionMapper<ExpressionV1>(this,
                (e, next) -> isVariable().apply(e).accept(new EitherVisitor<>() {
                    public ExpressionV1 left(VariableReference<ExpressionV1> left) {
                        if (left.name.equals(oldName)) {
                            return expressionFactory().variableReference(newName);
                        }
                        return e;
                    }

                    public ExpressionV1 right(ExpressionV1 right) {
                        return next.get();
                    }
                }), (e, visitor) -> e.accept(visitor));
    }

    public Function<ExpressionV1, String> i18nDict(String lang) {
        var i18nDict = OperationNamesI18n.operationNamesByLanguage().get(lang);
        if (i18nDict == null)
            return null;
        return dictGetter(i18nDict);
    }

}