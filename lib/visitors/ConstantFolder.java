package lib.visitors;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import lib.expression.*;
import lib.expression.category.*;
import port.IExpressionFactory;

public class ConstantFolder implements Function<Expression, Expression> {
    private final IExpressionFactory<Expression> factory;
    private final CategoryVisitor cat = new CategoryVisitor();

    ConstantFolder(IExpressionFactory<Expression> factory) {
        this.factory = factory;
    }

    private Expression foldOnce(Expression expression) {
        return expression.accept(cat).accept(new CategoryConstantFolderOnce(factory, expression));
    }

    @Override
    public Expression apply(Expression expression) {
        ExpressionMapper mapper = new ExpressionMapper(factory, (_, recurse) -> foldOnce(recurse.get()));
        return mapper.apply(expression);
    }

}

class ExpressionLiteral {
    protected final IExpressionFactory<Expression> factory;

    ExpressionLiteral(IExpressionFactory<Expression> factory) {
        this.factory = factory;
    }

    protected Expression whenBoth(Expression left, Expression right, Expression otherwise,
            BiFunction<Literal, Literal, String> whenBoth) {
        return whenLiteral(left, otherwise, leftLiteral -> whenLiteral(right, otherwise,
                rightLiteral -> factory.literal(whenBoth.apply(leftLiteral, rightLiteral))));
    }

    protected Expression whenLiteral(Expression expression, Expression otherwise,
            Function<Literal, Expression> whenLiteral) {
        return expression.accept(new FallbackVisitor<>(_e -> otherwise) {
            public Expression visit(Literal literal) {
                return whenLiteral.apply(literal);
            }
        });
    }

}

class CategoryConstantFolderOnce extends ExpressionLiteral implements CategoryExpressionVisitor<Expression> {
    private final Expression e;

    public CategoryConstantFolderOnce(IExpressionFactory<Expression> factory, Expression e) {

        super(factory);
        this.e = e;
    }

    @Override
    public Expression visit(LeafExpression _e) {
        return e;
    }

    @Override
    public Expression visit(OddExpression ex) {
        return ex.accept(new OddExpressionVisitor<Expression>() {
            public Expression visit(Conditional c) {
                return whenLiteral(c.condition, c,
                        literal -> literal.asInt() != 0 ? c.whenTrue : c.whenFalse);
            };

            public Expression visit(LogicalNot e) {
                return whenLiteral(e.operand, e,
                        literal -> factory.literal(literal.asInt() == 0 ? "1" : "0"));
            };

            public Expression visit(Negation e) {
                return whenLiteral(e.operand, e,
                        literal -> factory.literal(Integer.toString(-literal.asInt())));
            };
        });
    }

    @Override
    public Expression visit(BinaryExpression b) {
        return b.accept(new BinaryConstantFolder(factory, e));
    }

    @Override
    public Expression visit(FunctionCall _e) {
        return e;
    }

}
