package lib.handlers;

import lib.expression.*;

public class ExpressionSummaryReporter extends AbstractExpressionFunction<String> {
    ExpressionSummaryReporter() {}

    private String summarize(Expression expression) {
        var nodeCount = new NodeCounter().apply(expression);
        var leafCount = new LeafCounter().apply(expression);
        var depth = new DepthCalculator().apply(expression);
        var variables = new VariableCollector().apply(expression);
        var literals = new LiteralCollector().apply(expression);
        return "nodes=" + nodeCount + ", leaves=" + leafCount + ", depth=" + depth + ", variables=" + variables + ", literals=" + literals;
    }

    public String visit(Literal expression) { return summarize(expression); }
    public String visit(VariableReference expression) { return summarize(expression); }
    public String visit(Addition expression) { return summarize(expression); }
    public String visit(Subtraction expression) { return summarize(expression); }
    public String visit(Multiplication expression) { return summarize(expression); }
    public String visit(Division expression) { return summarize(expression); }
    public String visit(Negation expression) { return summarize(expression); }
    public String visit(Modulo expression) { return summarize(expression); }
    public String visit(Exponentiation expression) { return summarize(expression); }
    public String visit(Equality expression) { return summarize(expression); }
    public String visit(Inequality expression) { return summarize(expression); }
    public String visit(LessThan expression) { return summarize(expression); }
    public String visit(GreaterThan expression) { return summarize(expression); }
    public String visit(LessThanOrEqual expression) { return summarize(expression); }
    public String visit(GreaterThanOrEqual expression) { return summarize(expression); }
    public String visit(Conjunction expression) { return summarize(expression); }
    public String visit(Disjunction expression) { return summarize(expression); }
    public String visit(LogicalNot expression) { return summarize(expression); }
    public String visit(Conditional expression) { return summarize(expression); }
    public String visit(FunctionCall expression) { return summarize(expression); }
}