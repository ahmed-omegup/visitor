package lib.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.FunctionCall;

public class FunctionArityCollector implements Function<Expression, List<Integer>> {
    FunctionArityCollector() {}

    public List<Integer> apply(Expression expression) {
        var arities = new ArrayList<Integer>();
        var handler = new RecursiveExpression(new FunctionArityCollectorVisitor(arities));
        handler.accept(expression);
        return arities;
    }
}

final class FunctionArityCollectorVisitor extends EmptyVisitor<Void> implements Consumer<Expression> {
    private final List<Integer> arities;

    FunctionArityCollectorVisitor(List<Integer> arities) {
        this.arities = arities;
    }

    public void accept(Expression expression) {
        expression.accept(this);
    }

    public Void visit(FunctionCall expression) {
        arities.add(expression.arguments.size());
        return null;
    }
}