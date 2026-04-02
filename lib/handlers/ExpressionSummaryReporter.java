package lib.handlers;

public class ExpressionSummaryReporter {
    public String handle(lib.expression.Expression expression) {
        var nodeCount = new NodeCounter().handle(expression);
        var leafCount = new LeafCounter().handle(expression);
        var depth = new DepthCalculator().handle(expression);
        var variables = new VariableCollector().handle(expression);
        var literals = new LiteralCollector().handle(expression);
        return "nodes=" + nodeCount + ", leaves=" + leafCount + ", depth=" + depth + ", variables=" + variables + ", literals=" + literals;
    }
}