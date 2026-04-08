package lib.expression;

public class LambdaExpression implements ExpressionV2 {
    LambdaExpression(String parameterName, ExpressionV2 body) {
        this.parameterName = parameterName;
        this.body = body;
    }

    public <R> R accept(ExpressionVisitor2<R> visitor) {
        return visitor.visit(this);
    }

    public final String parameterName;
    public final ExpressionV2 body;
}