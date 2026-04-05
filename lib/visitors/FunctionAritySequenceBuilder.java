package lib.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lib.expression.*;

public class FunctionAritySequenceBuilder implements Function<Expression, List<Integer>> {
    FunctionAritySequenceBuilder() {}

    public List<Integer> apply(Expression expression) {
        var arities = new ArrayList<Integer>();
        expression.accept(new RecursiveExpressionVisitor(new FunctionAritySequenceBuilderVisitor(arities)));
        return arities;
    }
}

final class FunctionAritySequenceBuilderVisitor extends EmptyVisitor {
    private final List<Integer> arities;

    FunctionAritySequenceBuilderVisitor(List<Integer> arities) {
        this.arities = arities;
    }

    public Void visit(FunctionCall expression) { arities.add(expression.arguments.size()); return null; }
}