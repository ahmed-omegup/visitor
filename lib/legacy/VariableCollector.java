package lib.legacy;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.VariableReference;

public class VariableCollector implements Function<Expression, Set<String>> {
    VariableCollector() {}

    public Set<String> apply(Expression expression) {
        var names = new LinkedHashSet<String>();
        var handler = new RecursiveExpression(new VariableCollectorVisitor(names));
        handler.accept(expression);
        return names;
    }
}

final class VariableCollectorVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final Set<String> names;

    VariableCollectorVisitor(Set<String> names) {
        this.names = names;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(VariableReference expression) {
        names.add(expression.name);
        return null;
    }
}