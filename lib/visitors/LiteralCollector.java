package lib.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.Literal;

public class LiteralCollector implements Function<Expression, List<String>> {
    LiteralCollector() {}

    public List<String> apply(Expression expression) {
        var literals = new ArrayList<String>();
        expression.accept(new RecursiveExpressionVisitor(new LiteralCollectorVisitor(literals)));
        return literals;
    }
}

final class LiteralCollectorVisitor extends EmptyVisitor {
    private final List<String> literals;

    LiteralCollectorVisitor(List<String> literals) {
        this.literals = literals;
    }

    public Void visit(Literal expression) {
        literals.add(expression.value);
        return null;
    }
}