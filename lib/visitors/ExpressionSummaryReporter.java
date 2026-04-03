package lib.visitors;

import lib.expression.*;

public class ExpressionSummaryReporter implements Visitor<String> {
    ExpressionSummaryReporter() {}

    public String handle(Expression expression) {
        var nodeCount = expression.accept(new NodeCounter());
        var leafCount = expression.accept(new LeafCounter());
        var depth = expression.accept(new DepthCalculator());
        var variables = expression.accept(new VariableCollector());
        var literals = expression.accept(new LiteralCollector());
        return "nodes=" + nodeCount + ", leaves=" + leafCount + ", depth=" + depth + ", variables=" + variables + ", literals=" + literals;
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