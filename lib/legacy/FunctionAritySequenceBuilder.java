package lib.legacy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.*;

public class FunctionAritySequenceBuilder implements Function<Expression, List<Integer>> {
    FunctionAritySequenceBuilder() {}

    public List<Integer> apply(Expression expression) {
        var arities = new ArrayList<Integer>();
        var handler = new RecursiveExpression(new FunctionAritySequenceBuilderVisitor(arities));
        handler.accept(expression);
        return arities;
    }
}

final class FunctionAritySequenceBuilderVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final List<Integer> arities;

    FunctionAritySequenceBuilderVisitor(List<Integer> arities) {
        this.arities = arities;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(FunctionCall expression) { arities.add(expression.arguments.size()); return null; }
}