package lib.handlers;

import lib.expression.*;

public class ExpressionFingerprintReporter implements Visitor<String> {
    ExpressionFingerprintReporter() {}

    public String handle(Expression expression) {
        var nodes = expression.accept(new NodeCounter());
        var depth = expression.accept(new DepthCalculator());
        var structure = expression.accept(new StructuralSignatureBuilder());
        var structureHash = expression.accept(new StructuralHashBuilder());
        return "nodes=" + nodes + ";depth=" + depth + ";hash=" + structureHash + ";shape=" + structure;
    }

    public String visit(Literal expression) { return handle(expression); }
    public String visit(VariableReference expression) { return handle(expression); }
    public String visit(Addition expression) { return handle(expression); }
    public String visit(Subtraction expression) { return handle(expression); }
    public String visit(Multiplication expression) { return handle(expression); }
    public String visit(Division expression) { return handle(expression); }
    public String visit(Negation expression) { return handle(expression); }
    public String visit(Modulo expression) { return handle(expression); }
    public String visit(Exponentiation expression) { return handle(expression); }
    public String visit(Equality expression) { return handle(expression); }
    public String visit(Inequality expression) { return handle(expression); }
    public String visit(LessThan expression) { return handle(expression); }
    public String visit(GreaterThan expression) { return handle(expression); }
    public String visit(LessThanOrEqual expression) { return handle(expression); }
    public String visit(GreaterThanOrEqual expression) { return handle(expression); }
    public String visit(Conjunction expression) { return handle(expression); }
    public String visit(Disjunction expression) { return handle(expression); }
    public String visit(LogicalNot expression) { return handle(expression); }
    public String visit(Conditional expression) { return handle(expression); }
    public String visit(FunctionCall expression) { return handle(expression); }
}