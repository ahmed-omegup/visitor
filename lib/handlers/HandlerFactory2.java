package lib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.dict.BindingPowersDict;
import lib.dict.ClassNamesDict;
import lib.dict.ConstDict;
import lib.dict.Dict;
import lib.dict.OperationNamesI18n;
import lib.expression.ExpressionV1T2;
import lib.expression.ExpressionV2;
import lib.expression.ExpressionVisitor;
import lib.expression.ExpressionVisitor2;
import lib.expression.Factory2;
import lib.expression.Literal;
import lib.expression.Negation2;
import lib.expression.VariableReference;
import lib.utils.Either;
import lib.utils.EitherVisitor;
import lib.utils.Left;
import lib.utils.Right;
import lib.visitors.ConstantFolder;
import lib.visitors.ConstantFolderOnce;
import lib.visitors.ExpressionChildren;
import lib.visitors.ExpressionMapper;
import lib.visitors.ExpressionToJsLikeSyntax;
import lib.visitors.ExpressionToLispLikeSyntax;
import lib.visitors.FallbackVisitor;
import lib.visitors.IntegerEvaluationVisitor;
import lib.visitors.IsomorphicGetter;
import lib.visitors.IsomorphicSetter;
import port.BindingPower;
import port.IExpressionDict;
import port.IExpressionDict2;
import port.IExpressionFactory2;
import port.IHandlerFactory2;
import port.State;

public class HandlerFactory2 implements IHandlerFactory2<ExpressionV2> {
    private static final class Dict2<T> extends Dict<T> implements IExpressionDict2<T> {
        private T negation2;

        @Override
        public T negation2() {
            return negation2;
        }
    }

    @Override
    public IExpressionFactory2<ExpressionV2> expressionFactory() {
        return new Factory2();
    }

    private <R> R accept(ExpressionV2 expression, ExpressionVisitor<R, ExpressionV2> visitor,
            Function<Negation2, R> negation2Handler) {
        return expression.accept(new ExpressionVisitor2<R>() {
            @Override
            public R visit(ExpressionV1T2 e) {
                return e.wrappee.accept(visitor);
            }

            @Override
            public R visit(Negation2 e) {
                return negation2Handler.apply(e);
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
            _negation2 -> new Right<>(expression)
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
            _negation2 -> new Right<>(expression)
        );
    }

    @Override
    public Function<ExpressionV2, List<ExpressionV2>> expressionChildren() {
        var children = new ExpressionChildren<ExpressionV2>();
        return expression -> accept(expression, children, negation2 -> List.of(negation2.operand));
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

    public <T> Function<ExpressionV2, T> dictGetter(Dict<T> values, T negation2Value) {
        var visitor = new IsomorphicGetter<T, ExpressionV2>(values);
        return expression -> accept(expression, visitor, _negation2 -> negation2Value);
    }

    @Override
    public Function<ExpressionV2, String> expressionClassNameExtractor() {
        return dictGetter(new ClassNamesDict(), "Negation2");
    }

    @Override
    public Function<ExpressionV2, BindingPower> createBindingPowerHandler() {
        return dictGetter(new BindingPowersDict(), new BindingPower(30, true));
    }

    @Override
    public Function<ExpressionV2, ExpressionV2> constantFolderOnce() {
        return expression -> accept(
            expression,
            new ConstantFolderOnce<>(expressionFactory(), expression, isLiteral()),
            negation2 -> isLiteral().apply(negation2.operand)
                .accept(new EitherVisitor<Literal<ExpressionV2>, ExpressionV2, ExpressionV2>() {
                    @Override
                    public ExpressionV2 left(Literal<ExpressionV2> left) {
                        return expressionFactory().literal(Integer.toString(-left.asInt()));
                    }

                    @Override
                    public ExpressionV2 right(ExpressionV2 right) {
                        return expression;
                    }
                })
        );
    }

    @Override
    public Function<ExpressionV2, ExpressionV2> constantFolder() {
        return new ConstantFolder<>(this);
    }

    private ExpressionV2 mapWithVisitor(ExpressionV2 expression, ExpressionMapper<ExpressionV2> visitor) {
        return accept(expression, visitor, negation2 -> expressionFactory().negation2(visitor.apply(negation2.operand)));
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
            negation2 -> "neg2(" + jsLikeSyntaxPrinter().apply(negation2.operand) + ")"
        );
    }

    @Override
    public Function<ExpressionV2, String> lispLikeSyntaxPrinter() {
        return expression -> accept(
            expression,
            new ExpressionToLispLikeSyntax<>(this),
            negation2 -> "(neg2 " + lispLikeSyntaxPrinter().apply(negation2.operand) + ")"
        );
    }

    private Integer evaluateWithVisitor(ExpressionV2 expression, IntegerEvaluationVisitor<ExpressionV2> visitor) {
        return accept(expression, visitor, negation2 -> -evaluateWithVisitor(negation2.operand, visitor));
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

    public <T> State<ExpressionV2, Dict<T>, T> state() {
        return new State<ExpressionV2, Dict<T>, T>() {
            @Override
            public Dict<T> intial(T value) {
                return new ConstDict<T>(value);
            }

            @Override
            public Function<ExpressionV2, T> getter(Dict<T> state) {
                return dictGetter(state, state.negation);
            }

            @Override
            public Consumer<ExpressionV2> setter(Dict<T> state, T value) {
                var visitor = new IsomorphicSetter<T, ExpressionV2>(state, value);
                return expression -> accept(expression, visitor, _negation2 -> {
                    state.negation = value;
                    return null;
                });
            }
        };
    }

    @Override
    public <T, R> R handleState(port.StateConsumer<ExpressionV2, T, R> handler) {
        return handler.consume(this.state());
    }

    public <T> Function<ExpressionV2, Dict<T>> localReduceVisitor(T initial, BiFunction<T, ExpressionV2, T> reducer) {
        return new LocalReduceVisitor<ExpressionV2, Dict<T>, T>(state(), initial, reducer, this.expressionChildren());
    }

    @Override
    public Function<ExpressionV2, IExpressionDict<Integer>> histogram() {
        var visitor = localReduceVisitor(0, (count, _expression) -> count + 1);
        return expression -> visitor.apply(expression);
    }

    @Override
    public Function<ExpressionV2, IExpressionDict2<Integer>> histogram2() {
        return expression -> {
            var histogram = new Dict2<Integer>();
            histogram.literal = 0;
            histogram.variableReference = 0;
            histogram.addition = 0;
            histogram.subtraction = 0;
            histogram.multiplication = 0;
            histogram.division = 0;
            histogram.negation = 0;
            histogram.negation2 = 0;
            histogram.modulo = 0;
            histogram.exponentiation = 0;
            histogram.equality = 0;
            histogram.inequality = 0;
            histogram.lessThan = 0;
            histogram.greaterThan = 0;
            histogram.lessThanOrEqual = 0;
            histogram.greaterThanOrEqual = 0;
            histogram.conjunction = 0;
            histogram.disjunction = 0;
            histogram.logicalNot = 0;
            histogram.conditional = 0;
            histogram.functionCall = 0;
            countIntoHistogram2(expression, histogram);
            return histogram;
        };
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

    public Function<ExpressionV2, String> i18nDict(String lang) {
        var i18nDict = OperationNamesI18n.operationNamesByLanguage().get(lang);
        if (i18nDict == null) {
            return null;
        }
        return dictGetter(i18nDict, "negation2");
    }

    private void countIntoHistogram2(ExpressionV2 expression, Dict2<Integer> histogram) {
        switch (expressionClassNameExtractor().apply(expression)) {
            case "Literal" -> histogram.literal += 1;
            case "VariableReference" -> histogram.variableReference += 1;
            case "Addition" -> histogram.addition += 1;
            case "Subtraction" -> histogram.subtraction += 1;
            case "Multiplication" -> histogram.multiplication += 1;
            case "Division" -> histogram.division += 1;
            case "Negation" -> histogram.negation += 1;
            case "Negation2" -> histogram.negation2 += 1;
            case "Modulo" -> histogram.modulo += 1;
            case "Exponentiation" -> histogram.exponentiation += 1;
            case "Equality" -> histogram.equality += 1;
            case "Inequality" -> histogram.inequality += 1;
            case "LessThan" -> histogram.lessThan += 1;
            case "GreaterThan" -> histogram.greaterThan += 1;
            case "LessThanOrEqual" -> histogram.lessThanOrEqual += 1;
            case "GreaterThanOrEqual" -> histogram.greaterThanOrEqual += 1;
            case "Conjunction" -> histogram.conjunction += 1;
            case "Disjunction" -> histogram.disjunction += 1;
            case "LogicalNot" -> histogram.logicalNot += 1;
            case "Conditional" -> histogram.conditional += 1;
            case "FunctionCall" -> histogram.functionCall += 1;
            default -> throw new IllegalArgumentException("Unknown expression kind: " + expressionClassNameExtractor().apply(expression));
        }

        for (var child : expressionChildren().apply(expression)) {
            countIntoHistogram2(child, histogram);
        }
    }
}