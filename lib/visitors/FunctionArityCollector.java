package lib.visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lib.expression.Expression;
import lib.expression.FunctionCall;

public class FunctionArityCollector implements Function<Expression, List<Integer>> {
    FunctionArityCollector() {}

    public List<Integer> apply(Expression expression) {
        var arities = new ArrayList<Integer>();
        expression.accept(new RecursiveExpressionVisitor(new FunctionArityCollectorVisitor(arities)));
        return arities;
    }
}

final class FunctionArityCollectorVisitor extends EmptyVisitor {
    private final List<Integer> arities;

    FunctionArityCollectorVisitor(List<Integer> arities) {
        this.arities = arities;
    }

    public Void visit(FunctionCall expression) {
        arities.add(expression.arguments.size());
        return null;
    }
}