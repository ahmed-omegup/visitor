package lib.handlers;

public class ExpressionSummaryReporter extends AbstractHandlerVisitor<String> {
    ExpressionSummaryReporter() {}

    public String handle(lib.expression.Expression expression) {
        var nodeCount = expression.accept(new NodeCounter());
        var leafCount = expression.accept(new LeafCounter());
        var depth = expression.accept(new DepthCalculator());
        var variables = expression.accept(new VariableCollector());
        var literals = expression.accept(new LiteralCollector());
        return "nodes=" + nodeCount + ", leaves=" + leafCount + ", depth=" + depth + ", variables=" + variables + ", literals=" + literals;
    }
}