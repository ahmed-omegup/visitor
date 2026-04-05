package lib.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.Literal;
import lib.expression.VariableReference;

public class LeafLabelSequenceBuilder implements Function<Expression, List<String>> {
    LeafLabelSequenceBuilder() {}

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        expression.accept(new RecursiveExpressionVisitor(new LeafLabelSequenceBuilderVisitor(labels)));
        return labels;
    }
}

final class LeafLabelSequenceBuilderVisitor extends ExpressionVisitorAdapter {
    private final List<String> labels;

    LeafLabelSequenceBuilderVisitor(List<String> labels) {
        this.labels = labels;
    }

    public Void visit(Literal expression) {
        labels.add("literal:" + expression.value);
        return null;
    }

    public Void visit(VariableReference expression) {
        labels.add("variable:" + expression.name);
        return null;
    }
}