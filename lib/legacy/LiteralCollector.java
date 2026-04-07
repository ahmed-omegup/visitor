package lib.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.Literal;

public class LiteralCollector implements Function<Expression, List<String>> {
    LiteralCollector() {}

    public List<String> apply(Expression expression) {
        var literals = new ArrayList<String>();
        var handler = new RecursiveExpression(new LiteralCollectorVisitor(literals));
        handler.accept(expression);
        return literals;
    }
}

final class LiteralCollectorVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final List<String> literals;

    LiteralCollectorVisitor(List<String> literals) {
        this.literals = literals;
    }

    public Void visit(Literal expression) {
        literals.add(expression.value);
        return null;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }
}