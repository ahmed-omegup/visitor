package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.Literal;
import lib.expression.VariableReference;

public class DistinctLeafLabelCollector implements Function<Expression, Set<String>> {
    DistinctLeafLabelCollector() {}

    public Set<String> apply(Expression expression) {
        var labels = new LinkedHashSet<String>();
        var handler = new RecursiveExpression(new DistinctLeafLabelCollectorVisitor(labels));
        handler.accept(expression);
        return labels;
    }
}

final class DistinctLeafLabelCollectorVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final Set<String> labels;

    DistinctLeafLabelCollectorVisitor(Set<String> labels) {
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