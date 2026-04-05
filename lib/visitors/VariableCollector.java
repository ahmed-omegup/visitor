package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.VariableReference;

public class VariableCollector implements Function<Expression, Set<String>> {
    VariableCollector() {}

    public Set<String> apply(Expression expression) {
        var names = new LinkedHashSet<String>();
        expression.accept(new RecursiveExpressionVisitor(new VariableCollectorVisitor(names)));
        return names;
    }
}

final class VariableCollectorVisitor extends ExpressionVisitorAdapter {
    private final Set<String> names;

    VariableCollectorVisitor(Set<String> names) {
        this.names = names;
    }

    public Void visit(VariableReference expression) {
        names.add(expression.name);
        return null;
    }
}