package lib.handlers;

import lib.expression.Expression;

public class ExpressionFingerprintReporter extends AbstractHandlerVisitor<String> {
    ExpressionFingerprintReporter() {}

    public String handle(Expression expression) {
        var nodes = expression.accept(new NodeCounter());
        var depth = expression.accept(new DepthCalculator());
        var structure = expression.accept(new StructuralSignatureBuilder());
        var structureHash = expression.accept(new StructuralHashBuilder());
        return "nodes=" + nodes + ";depth=" + depth + ";hash=" + structureHash + ";shape=" + structure;
    }
}