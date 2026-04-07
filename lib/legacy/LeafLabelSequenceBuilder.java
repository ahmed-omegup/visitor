package lib.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.Literal;
import lib.expression.VariableReference;

public class LeafLabelSequenceBuilder implements Function<Expression, List<String>> {
    LeafLabelSequenceBuilder() {}

    public List<String> apply(Expression expression) {
        var labels = new ArrayList<String>();
        var handler = new RecursiveExpression(new LeafLabelSequenceBuilderVisitor(labels));
        handler.accept(expression);
        return labels;
    }
}

final class LeafLabelSequenceBuilderVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final List<String> labels;

    LeafLabelSequenceBuilderVisitor(List<String> labels) {
        this.labels = labels;
    }

    public void accept(Expression expression) {
        expression.accept(this);
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