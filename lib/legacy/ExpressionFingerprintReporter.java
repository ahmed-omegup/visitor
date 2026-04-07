package lib.handlers;

import lib.expression.*;

public class ExpressionFingerprintReporter extends AbstractExpressionFunction<String> {
    ExpressionFingerprintReporter() {}

    private String fingerprint(Expression expression) {
        var nodes = new NodeCounter().apply(expression);
        var depth = new DepthCalculator().apply(expression);
        var structure = new StructuralSignatureBuilder().apply(expression);
        var structureHash = new StructuralHashBuilder().apply(expression);
        return "nodes=" + nodes + ";depth=" + depth + ";hash=" + structureHash + ";shape=" + structure;
    }

    public String visit(Literal expression) { return fingerprint(expression); }
    public String visit(VariableReference expression) { return fingerprint(expression); }
    public String visit(Addition expression) { return fingerprint(expression); }
    public String visit(Subtraction expression) { return fingerprint(expression); }
    public String visit(Multiplication expression) { return fingerprint(expression); }
    public String visit(Division expression) { return fingerprint(expression); }
    public String visit(Negation expression) { return fingerprint(expression); }
    public String visit(Modulo expression) { return fingerprint(expression); }
    public String visit(Exponentiation expression) { return fingerprint(expression); }
    public String visit(Equality expression) { return fingerprint(expression); }
    public String visit(Inequality expression) { return fingerprint(expression); }
    public String visit(LessThan expression) { return fingerprint(expression); }
    public String visit(GreaterThan expression) { return fingerprint(expression); }
    public String visit(LessThanOrEqual expression) { return fingerprint(expression); }
    public String visit(GreaterThanOrEqual expression) { return fingerprint(expression); }
    public String visit(Conjunction expression) { return fingerprint(expression); }
    public String visit(Disjunction expression) { return fingerprint(expression); }
    public String visit(LogicalNot expression) { return fingerprint(expression); }
    public String visit(Conditional expression) { return fingerprint(expression); }
    public String visit(FunctionCall expression) { return fingerprint(expression); }
}