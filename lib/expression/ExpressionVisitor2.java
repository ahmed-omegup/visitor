package lib.expression;

import java.util.function.*;

public interface ExpressionVisitor2<R> {
    R visit(ExpressionV1T2 e);
    R visit(LambdaExpression e);
}
