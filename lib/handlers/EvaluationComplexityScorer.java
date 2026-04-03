package lib.handlers;

import lib.expression.Expression;

public class EvaluationComplexityScorer extends AbstractHandlerVisitor<Integer> {
    EvaluationComplexityScorer() {}

    public Integer handle(Expression expression) {
        var nodes = expression.accept(new NodeCounter());
        var depth = expression.accept(new DepthCalculator());
        var functionCalls = expression.accept(new FunctionCallCounter());
        var booleanOperators = expression.accept(new BooleanOperatorCounter());
        var comparisons = expression.accept(new ComparisonOperatorCounter());
        return nodes + (depth * 2) + (functionCalls * 3) + booleanOperators + comparisons;
    }
}