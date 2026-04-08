package lib.expression;

import java.util.List;
import port.IExpressionFactory2;

public class Factory2 extends AFactory<ExpressionV2> implements IExpressionFactory2<ExpressionV2> {

    @Override
    protected ExpressionV2 wrap(Expression<ExpressionV2> expression) {
        return new ExpressionV1T2(expression);
    }

    public ExpressionV2 lambdaExpression(String parameterName, ExpressionV2 body) {
        return new LambdaExpression(parameterName, body);
    }
}