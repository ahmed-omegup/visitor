package lib.handlers;

import lib.expression.*;

public class EvaluationComplexityScorer implements Visitor<Integer> {
    EvaluationComplexityScorer() {}

    public Integer handle(Expression expression) {
        var nodes = expression.accept(new NodeCounter());
        var depth = expression.accept(new DepthCalculator());
        var functionCalls = expression.accept(new FunctionCallCounter());
        var booleanOperators = expression.accept(new BooleanOperatorCounter());
        var comparisons = expression.accept(new ComparisonOperatorCounter());
        return nodes + (depth * 2) + (functionCalls * 3) + booleanOperators + comparisons;
    }

    public Integer visit(Literal expression) { return handle(expression); }
    public Integer visit(VariableReference expression) { return handle(expression); }
    public Integer visit(Addition expression) { return handle(expression); }
    public Integer visit(Subtraction expression) { return handle(expression); }
    public Integer visit(Multiplication expression) { return handle(expression); }
    public Integer visit(Division expression) { return handle(expression); }
    public Integer visit(Negation expression) { return handle(expression); }
    public Integer visit(Modulo expression) { return handle(expression); }
    public Integer visit(Exponentiation expression) { return handle(expression); }
    public Integer visit(Equality expression) { return handle(expression); }
    public Integer visit(Inequality expression) { return handle(expression); }
    public Integer visit(LessThan expression) { return handle(expression); }
    public Integer visit(GreaterThan expression) { return handle(expression); }
    public Integer visit(LessThanOrEqual expression) { return handle(expression); }
    public Integer visit(GreaterThanOrEqual expression) { return handle(expression); }
    public Integer visit(Conjunction expression) { return handle(expression); }
    public Integer visit(Disjunction expression) { return handle(expression); }
    public Integer visit(LogicalNot expression) { return handle(expression); }
    public Integer visit(Conditional expression) { return handle(expression); }
    public Integer visit(FunctionCall expression) { return handle(expression); }
}