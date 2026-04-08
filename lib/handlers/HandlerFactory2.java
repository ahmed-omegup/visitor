package lib.handlers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ds.BindingPower;
import ds.Dict;
import ds.Dict2;
import lib.dict.BindingPowersDict;
import lib.dict.ClassNamesDict;
import lib.dict.OperationNamesI18n;
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
import port.ConstantFolder;
import port.IExpressionDict;
import port.IExpressionDict2;
import port.IExpressionFactory2;
import port.IHandlerFactory2;
import port.State;

public class HandlerFactory2 implements IHandlerFactory2<ExpressionV2> {

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

    public Function<ExpressionV2, Either<Literal<ExpressionV2>, ExpressionV2>> isLiteral() {
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

    public Function<ExpressionV2, Either<VariableReference<ExpressionV2>, ExpressionV2>> isVariable() {
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

    @Override
    public Function<ExpressionV2, Boolean> literalChecker() {
        return expression -> isLiteral().apply(expression)
            .accept(new EitherVisitor<Literal<ExpressionV2>, ExpressionV2, Boolean>() {
                @Override
                public Boolean left(Literal<ExpressionV2> left) {
                    return true;
                }

                @Override
                public Boolean right(ExpressionV2 right) {
                    return false;
                }
            });
    }

    @Override
    public Function<ExpressionV2, Boolean> variableChecker() {
        return expression -> isVariable().apply(expression)
            .accept(new EitherVisitor<VariableReference<ExpressionV2>, ExpressionV2, Boolean>() {
                @Override
                public Boolean left(VariableReference<ExpressionV2> left) {
                    return true;
                }

                @Override
                public Boolean right(ExpressionV2 right) {
                    return false;
                }
            });
    }

    public <T> Function<ExpressionV2, T> dictGetter(Dict<T> values, T lambdaExpressionValue) {
        var visitor = new IsomorphicGetter<T, ExpressionV2>(values);
        return expression -> accept(expression, visitor, _lambdaExpression -> lambdaExpressionValue);
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
    public Function<ExpressionV2, ExpressionV2> constantFolderOnce() {
        return this::foldConstantOnce;
    }

    @Override
    public Function<ExpressionV2, ExpressionV2> constantFolder() {
        return new ConstantFolder<>(this);
    }

    private ExpressionV2 mapWithVisitor(ExpressionV2 expression, ExpressionMapper<ExpressionV2> visitor) {
        return accept(
            expression,
            visitor,
            lambdaExpression -> expressionFactory().lambdaExpression(
                lambdaExpression.parameterName,
                visitor.apply(lambdaExpression.body)
            )
        );
    }

    private ExpressionV2 foldConstantOnce(ExpressionV2 expression) {
        var lambdaCallFolded = foldLambdaCall(expression);
        if (lambdaCallFolded != expression) {
            return foldConstantOnce(lambdaCallFolded);
        }
        return accept(
            expression,
            new ConstantFolderOnce<>(expressionFactory(), expression, isLiteral()),
            _lambdaExpression -> expression
        );
    }

    private ExpressionV2 foldLambdaCall(ExpressionV2 expression) {
        return accept(
            expression,
            new FallbackVisitor<ExpressionV2, ExpressionV2>(_expression -> expression) {
                @Override
                public ExpressionV2 visit(FunctionCall<ExpressionV2> call) {
                    var lambda = asLambda(call.callee);
                    if (lambda == null || call.arguments.size() != 1) {
                        return expression;
                    }
                    return substitute(lambda.body, lambda.parameterName, call.arguments.get(0));
                }
            },
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

    private ExpressionV2 substitute(ExpressionV2 expression, String parameterName, ExpressionV2 replacement) {
        return isVariable().apply(expression).accept(new EitherVisitor<>() {
            @Override
            public ExpressionV2 left(VariableReference<ExpressionV2> left) {
                if (left.name.equals(parameterName)) {
                    return replacement;
                }
                return expression;
            }

            @Override
            public ExpressionV2 right(ExpressionV2 right) {
                var lambdaExpression = asLambda(expression);
                if (lambdaExpression != null) {
                    if (lambdaExpression.parameterName.equals(parameterName)) {
                        return expression;
                    }

                    var body = lambdaExpression.body;
                    var nestedParameterName = lambdaExpression.parameterName;
                    if (freeVariables(replacement).contains(nestedParameterName)) {
                        var freshName = freshVariableName(
                            nestedParameterName,
                            List.of(usedNames(body), usedNames(replacement), Set.of(parameterName))
                        );
                        body = renameBoundVariable(body, nestedParameterName, freshName);
                        nestedParameterName = freshName;
                    }

                    return expressionFactory().lambdaExpression(
                        nestedParameterName,
                        substitute(body, parameterName, replacement)
                    );
                }

                var mapper = new ExpressionMapper<ExpressionV2>(
                    HandlerFactory2.this,
                    (current, _next) -> substitute(current, parameterName, replacement),
                    HandlerFactory2.this::mapWithVisitor
                );
                return mapWithVisitor(expression, mapper);
            }
        });
    }

    private ExpressionV2 renameBoundVariable(ExpressionV2 expression, String oldName, String newName) {
        return isVariable().apply(expression).accept(new EitherVisitor<>() {
            @Override
            public ExpressionV2 left(VariableReference<ExpressionV2> left) {
                if (left.name.equals(oldName)) {
                    return expressionFactory().variableReference(newName);
                }
                return expression;
            }

            @Override
            public ExpressionV2 right(ExpressionV2 right) {
                var lambdaExpression = asLambda(expression);
                if (lambdaExpression != null) {
                    if (lambdaExpression.parameterName.equals(oldName)) {
                        return expression;
                    }
                    return expressionFactory().lambdaExpression(
                        lambdaExpression.parameterName,
                        renameBoundVariable(lambdaExpression.body, oldName, newName)
                    );
                }

                var mapper = new ExpressionMapper<ExpressionV2>(
                    HandlerFactory2.this,
                    (current, _next) -> renameBoundVariable(current, oldName, newName),
                    HandlerFactory2.this::mapWithVisitor
                );
                return mapWithVisitor(expression, mapper);
            }
        });
    }

    private Set<String> freeVariables(ExpressionV2 expression) {
        return accept(
            expression,
            new FallbackVisitor<Set<String>, ExpressionV2>(_expression -> {
                var variables = new LinkedHashSet<String>();
                for (var child : expressionChildren().apply(expression)) {
                    variables.addAll(freeVariables(child));
                }
                return variables;
            }) {
                @Override
                public Set<String> visit(Literal<ExpressionV2> literal) {
                    return new LinkedHashSet<>();
                }

                @Override
                public Set<String> visit(VariableReference<ExpressionV2> variableReference) {
                    return new LinkedHashSet<>(Set.of(variableReference.name));
                }
            },
            lambdaExpression -> {
                var variables = new LinkedHashSet<>(freeVariables(lambdaExpression.body));
                variables.remove(lambdaExpression.parameterName);
                return variables;
            }
        );
    }

    private Set<String> usedNames(ExpressionV2 expression) {
        return accept(
            expression,
            new FallbackVisitor<Set<String>, ExpressionV2>(_expression -> {
                var names = new LinkedHashSet<String>();
                for (var child : expressionChildren().apply(expression)) {
                    names.addAll(usedNames(child));
                }
                return names;
            }) {
                @Override
                public Set<String> visit(Literal<ExpressionV2> literal) {
                    return new LinkedHashSet<>();
                }

                @Override
                public Set<String> visit(VariableReference<ExpressionV2> variableReference) {
                    return new LinkedHashSet<>(Set.of(variableReference.name));
                }
            },
            lambdaExpression -> {
                var names = new LinkedHashSet<>(usedNames(lambdaExpression.body));
                names.add(lambdaExpression.parameterName);
                return names;
            }
        );
    }

    private String freshVariableName(String baseName, List<Set<String>> usedNameSets) {
        var usedNames = new LinkedHashSet<String>();
        for (var names : usedNameSets) {
            usedNames.addAll(names);
        }

        var candidate = baseName;
        var suffix = 1;
        while (usedNames.contains(candidate)) {
            candidate = baseName + suffix;
            suffix += 1;
        }
        return candidate;
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

    private Integer evaluateWithVisitor(ExpressionV2 expression, IntegerEvaluationVisitor<ExpressionV2> visitor) {
        return accept(expression, visitor, _lambdaExpression -> {
            throw new IllegalArgumentException("Cannot directly evaluate a lambda expression");
        });
    }

    @Override
    public Function<ExpressionV2, Integer> integerEvaluator(Map<String, Integer> variables,
            Map<String, Function<List<Integer>, Integer>> functions) {
        var evaluator = new IntegerEvaluationVisitor<ExpressionV2>(variables, functions, isVariable(), this::evaluateWithVisitor);
        return expression -> evaluateWithVisitor(expression, evaluator);
    }

    public Function<ExpressionV2, List<String>> collectClassNamesVisitor() {
        var classNames = expressionClassNameExtractor();
        return new GlobalReduceVisitor<>(e -> new ArrayList<>(List.of(classNames.apply(e))), (left, right) -> {
            left.addAll(right);
            return left;
        }, this.expressionChildren());
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

    @Override
    public Function<ExpressionV2, ExpressionV2> renameVariable(String oldName, String newName) {
        return new ExpressionMapper<ExpressionV2>(this,
            (expression, next) -> isVariable().apply(expression).accept(new EitherVisitor<>() {
                @Override
                public ExpressionV2 left(VariableReference<ExpressionV2> left) {
                    if (left.name.equals(oldName)) {
                        return expressionFactory().variableReference(newName);
                    }
                    return expression;
                }

                @Override
                public ExpressionV2 right(ExpressionV2 right) {
                    return next.get();
                }
            }),
            this::mapWithVisitor
        );
    }

    @Override
    public Function<ExpressionV2, String> i18nDict(String lang) {
        var i18nDict = OperationNamesI18n.operationNamesByLanguage().get(lang);
        if (i18nDict == null) {
            return null;
        }
        return dictGetter(i18nDict, "lambdaExpression");
    }

}