package lib.utils;

import java.util.List;
import java.util.function.Function;

import lib.expression.ExpressionV1T2;
import lib.expression.ExpressionV2;
import lib.expression.ExpressionVisitor;
import lib.expression.ExpressionVisitor2;
import lib.expression.FunctionCall;
import lib.expression.LambdaExpression;
import lib.expression.Literal;
import lib.expression.VariableReference;
import lib.visitors.ExpressionChildren;
import lib.visitors.ExpressionMapper;
import lib.visitors.FallbackVisitor;
import lib.visitors.IntegerEvaluationVisitor;
import lib.visitors.IsomorphicGetter;
import port.IExpressionDict;
import port.IExpressionFactory2;

public class ExpressionV2Support {
    private final IExpressionFactory2<ExpressionV2> factory;
    private final ExpressionChildren<ExpressionV2> children = new ExpressionChildren<>();

    public ExpressionV2Support(IExpressionFactory2<ExpressionV2> factory) {
        this.factory = factory;
    }

    public <R> R accept(ExpressionV2 expression, ExpressionVisitor<R, ExpressionV2> visitor,
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

    public Function<ExpressionV2, List<ExpressionV2>> expressionChildren() {
        return expression -> accept(expression, children, lambdaExpression -> List.of(lambdaExpression.body));
    }

    public <T> Function<ExpressionV2, T> dictGetter(IExpressionDict<T> values, T lambdaExpressionValue) {
        var visitor = new IsomorphicGetter<T, ExpressionV2>(values);
        return expression -> accept(expression, visitor, _lambdaExpression -> lambdaExpressionValue);
    }

    public ExpressionV2 mapWithVisitor(ExpressionV2 expression, ExpressionMapper<ExpressionV2> visitor) {
        return accept(
            expression,
            visitor,
            lambdaExpression -> factory.lambdaExpression(
                lambdaExpression.parameterName,
                visitor.apply(lambdaExpression.body)
            )
        );
    }

    public LambdaExpression asLambda(ExpressionV2 expression) {
        return accept(
            expression,
            new FallbackVisitor<LambdaExpression, ExpressionV2>(_expression -> null) {
            },
            lambdaExpression -> lambdaExpression
        );
    }

    public FunctionCall<ExpressionV2> asFunctionCall(ExpressionV2 expression) {
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

    public String variableName(ExpressionV2 expression) {
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

    public Integer evaluateWithVisitor(ExpressionV2 expression, IntegerEvaluationVisitor<ExpressionV2> visitor) {
        return accept(expression, visitor, _lambdaExpression -> {
            throw new IllegalArgumentException("Cannot directly evaluate a lambda expression");
        });
    }
}