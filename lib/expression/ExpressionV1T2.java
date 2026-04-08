package lib.expression;

public class ExpressionV1T2 implements ExpressionV2 {
    public final Expression<ExpressionV2> wrappee;

    public ExpressionV1T2(Expression<ExpressionV2> wrappee) {
        this.wrappee = wrappee;
    }
    
    public <R> R accept(ExpressionVisitor2<R> visitor) {
        return visitor.visit(this);
    }

}
