package lib.handlers;

import lib.expression.Expression;

public class EvaluationComplexityScorer {
    public Integer handle(Expression expression) {
        var nodes = new NodeCounter().handle(expression);
        var depth = new DepthCalculator().handle(expression);
        var functionCalls = new FunctionCallCounter().handle(expression);
        var booleanOperators = new BooleanOperatorCounter().handle(expression);
        var comparisons = new ComparisonOperatorCounter().handle(expression);
        return nodes + (depth * 2) + (functionCalls * 3) + booleanOperators + comparisons;
    }
}