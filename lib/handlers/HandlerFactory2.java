package lib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import ds.BindingPower;
import ds.Dict;
import ds.Dict2;
import lib.dict.BindingPowersDict;
import lib.dict.ClassNamesDict;
import lib.expression.ExpressionV1T2;
import lib.expression.ExpressionV2;
import lib.expression.ExpressionVisitor;
import lib.expression.ExpressionVisitor2;
import lib.expression.Factory2;
import lib.expression.FunctionCall;
import lib.expression.LambdaExpression;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.utils.Either;
import lib.utils.EitherVisitor;
import lib.utils.LambdaCallFolder;
import lib.utils.Left;
import lib.utils.Right;
import lib.visitors.ConstantFolderOnce;
import lib.visitors.ExpressionChildren;
import lib.visitors.ExpressionMapper;
import lib.visitors.ExpressionToJsLikeSyntax;
import lib.visitors.ExpressionToLispLikeSyntax;
import lib.visitors.FallbackVisitor;
import lib.visitors.IntegerEvaluationVisitor;
import lib.visitors.IsomorphicGetter;
import lib.visitors.IsomorphicSetter;
import port.IExpressionDict;
import port.IExpressionDict2;
import port.IExpressionFactory2;
import port.IHandlerFactory2;
import port.State;

public class HandlerFactory2 extends HandlerFactoryBase<ExpressionV2> implements IHandlerFactory2<ExpressionV2> {

    @Override
    public IExpressionFactory2<ExpressionV2> expressionFactory() {
        return new Factory2();
    }

    private <R> R accept(ExpressionV2 expression, ExpressionVisitor<R, ExpressionV2> visitor,
            Function<LambdaExpression, R> lambdaExpressionHandler) {
        return expression.accept(new ExpressionVisitor2<R>() {
            @Override
            public R visit(ExpressionV1T2 e) {
                return e.wrappee.accept(visitor);
            }

            @Override
            public R visit(LambdaExpression e) {
                return lambdaExpressionHandler.apply(e);
            }
        });
    }

    @Override
    protected Function<ExpressionV2, Either<Literal<ExpressionV2>, ExpressionV2>> isLiteral() {
        return expression -> accept(
            expression,
            new FallbackVisitor<Either<Literal<ExpressionV2>, ExpressionV2>, ExpressionV2>(_e -> new Right<>(expression)) {
                @Override
                public Either<Literal<ExpressionV2>, ExpressionV2> visit(Literal<ExpressionV2> e) {
                    return new Left<>(e);
                }
            },
            _lambdaExpression -> new Right<>(expression)
        );
    }

    @Override
    protected Function<ExpressionV2, Either<VariableReference<ExpressionV2>, ExpressionV2>> isVariable() {
        return expression -> accept(
            expression,
            new FallbackVisitor<Either<VariableReference<ExpressionV2>, ExpressionV2>, ExpressionV2>(_e -> new Right<>(expression)) {
                @Override
                public Either<VariableReference<ExpressionV2>, ExpressionV2> visit(VariableReference<ExpressionV2> e) {
                    return new Left<>(e);
                }
            },
            _lambdaExpression -> new Right<>(expression)
        );
    }

    @Override
    public Function<ExpressionV2, List<ExpressionV2>> expressionChildren() {
        var children = new ExpressionChildren<ExpressionV2>();
        return expression -> accept(expression, children, lambdaExpression -> List.of(lambdaExpression.body));
    }

    public <T> Function<ExpressionV2, T> dictGetter(IExpressionDict<T> values, T lambdaExpressionValue) {
        var visitor = new IsomorphicGetter<T, ExpressionV2>(values);
        return expression -> accept(expression, visitor, _lambdaExpression -> lambdaExpressionValue);
    }

    @Override
    public <T> Function<ExpressionV2, T> dictReader(IExpressionDict2<T> values) {
        return dictGetter(values, values.lambdaExpression());
    }

    @Override
    protected <T> Function<ExpressionV2, T> readDict(IExpressionDict<T> values) {
        return dictGetter(values, null);
    }

    @Override
    public Function<ExpressionV2, String> expressionClassNameExtractor() {
        return dictGetter(new ClassNamesDict(), "LambdaExpression");
    }

    @Override
    public Function<ExpressionV2, BindingPower> createBindingPowerHandler() {
        return dictGetter(new BindingPowersDict(), new BindingPower(30, true));
    }

    @Override
    protected ExpressionV2 mapWithVisitor(ExpressionV2 expression, ExpressionMapper<ExpressionV2> visitor) {
        return accept(
            expression,
            visitor,
            lambdaExpression -> expressionFactory().lambdaExpression(
                lambdaExpression.parameterName,
                visitor.apply(lambdaExpression.body)
            )
        );
    }

    @Override
    protected ExpressionV2 foldConstantOnce(ExpressionV2 expression) {
        var lambdaCallFolded = lambdaCallFolder().foldCall(expression);
        if (lambdaCallFolded != expression) {
            return foldConstantOnce(lambdaCallFolded);
        }
        return accept(
            expression,
            new ConstantFolderOnce<>(expressionFactory(), expression, isLiteral()),
            _lambdaExpression -> expression
        );
    }

    private LambdaExpression asLambda(ExpressionV2 expression) {
        return accept(
            expression,
            new FallbackVisitor<LambdaExpression, ExpressionV2>(_expression -> null) {
            },
            lambdaExpression -> lambdaExpression
        );
    }

    private String variableName(ExpressionV2 expression) {
        return isVariable().apply(expression).accept(new EitherVisitor<>() {
            @Override
            public String left(VariableReference<ExpressionV2> left) {
                return left.name;
            }

            @Override
            public String right(ExpressionV2 right) {
                return null;
            }
        });
    }

    private FunctionCall<ExpressionV2> asFunctionCall(ExpressionV2 expression) {
        return accept(
            expression,
            new FallbackVisitor<FunctionCall<ExpressionV2>, ExpressionV2>(_expression -> null) {
                @Override
                public FunctionCall<ExpressionV2> visit(FunctionCall<ExpressionV2> call) {
                    return call;
                }
            },
            _lambdaExpression -> null
        );
    }

    private ExpressionV2 mapChildren(ExpressionV2 expression, UnaryOperator<ExpressionV2> recurse) {
        var mapper = new ExpressionMapper<ExpressionV2>(
            this,
            (current, _next) -> recurse.apply(current),
            this::mapWithVisitor
        );
        return mapWithVisitor(expression, mapper);
    }

    private LambdaCallFolder<ExpressionV2, LambdaExpression> lambdaCallFolder() {
        return new LambdaCallFolder<>(
            this::variableName,
            expressionFactory()::variableReference,
            this::asLambda,
            lambda -> lambda.parameterName,
            lambda -> lambda.body,
            expressionFactory()::lambdaExpression,
            this.expressionChildren(),
            this::mapChildren,
            this::asFunctionCall
        );
    }

    @Override
    public Function<ExpressionV2, ExpressionV2> expressionMapper(
            BiFunction<ExpressionV2, Supplier<ExpressionV2>, ExpressionV2> recurse) {
        var mapper = new ExpressionMapper<ExpressionV2>(this, recurse, this::mapWithVisitor);
        return mapper::apply;
    }

    @Override
    public Function<ExpressionV2, String> jsLikeSyntaxPrinter() {
        return expression -> accept(
            expression,
            new ExpressionToJsLikeSyntax<>(this, this.jsLikeSyntaxPrinter(), expression),
            lambdaExpression -> lambdaExpression.parameterName + " => " + jsLikeSyntaxPrinter().apply(lambdaExpression.body)
        );
    }

    @Override
    public Function<ExpressionV2, String> lispLikeSyntaxPrinter() {
        return expression -> accept(
            expression,
            new ExpressionToLispLikeSyntax<>(this),
            lambdaExpression -> "(lambda (" + lambdaExpression.parameterName + ") " + lispLikeSyntaxPrinter().apply(lambdaExpression.body) + ")"
        );
    }

    @Override
    protected Integer evaluateWithVisitor(ExpressionV2 expression, IntegerEvaluationVisitor<ExpressionV2> visitor) {
        return accept(expression, visitor, _lambdaExpression -> {
            throw new IllegalArgumentException("Cannot directly evaluate a lambda expression");
        });
    }

    public <T> State<ExpressionV2, Dict2<T>, T> state() {
        return new State<ExpressionV2, Dict2<T>, T>() {
            @Override
            public Dict2<T> intial(T value) {
                return new Dict2<T>(value);
            }

            @Override
            public Function<ExpressionV2, T> getter(Dict2<T> state) {
                return dictGetter(state, state.lambdaExpression);
            }

            @Override
            public Consumer<ExpressionV2> setter(Dict2<T> state, T value) {
                var visitor = new IsomorphicSetter<T, ExpressionV2>(state, value);
                return expression -> accept(expression, visitor, _lambdaExpression -> {
                    state.lambdaExpression = value;
                    return null;
                });
            }
        };
    }

    @Override
    public <T, R> R handleState(port.StateConsumer<ExpressionV2, T, R> handler) {
        return handler.consume(this.state());
    }

    public <T> Function<ExpressionV2, Dict2<T>> localReduceVisitor(T initial, BiFunction<T, ExpressionV2, T> reducer) {
        return new LocalReduceVisitor<ExpressionV2, Dict2<T>, T>(state(), initial, reducer, this.expressionChildren());
    }

    @Override
    public Function<ExpressionV2, IExpressionDict<Integer>> histogram() {
        var visitor = localReduceVisitor(0, (count, _expression) -> count + 1);
        return expression -> visitor.apply(expression);
    }

    @Override
    public Function<ExpressionV2, IExpressionDict2<Integer>> histogram2() {
        var visitor = localReduceVisitor(0, (count, _expression) -> count + 1);
        return expression -> visitor.apply(expression);
    }
}