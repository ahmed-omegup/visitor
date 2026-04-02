package lib.handlers;

import lib.expression.Expression;

public class ExpressionFingerprintReporter {
    public String handle(Expression expression) {
        var nodes = new NodeCounter().handle(expression);
        var depth = new DepthCalculator().handle(expression);
        var structure = new StructuralSignatureBuilder().handle(expression);
        var structureHash = new StructuralHashBuilder().handle(expression);
        return "nodes=" + nodes + ";depth=" + depth + ";hash=" + structureHash + ";shape=" + structure;
    }
}