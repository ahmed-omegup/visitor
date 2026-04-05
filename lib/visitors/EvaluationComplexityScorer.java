package lib.visitors;

import lib.expression.*;

public class EvaluationComplexityScorer extends AbstractExpressionFunction<Integer> {
    EvaluationComplexityScorer() {}

    private Integer score(Expression expression) {
        var nodes = new NodeCounter().apply(expression);
        var depth = new DepthCalculator().apply(expression);
        var functionCalls = new FunctionCallCounter().apply(expression);
        var booleanOperators = new BooleanOperatorCounter().apply(expression);
        var comparisons = new ComparisonOperatorCounter().apply(expression);
        return nodes + (depth * 2) + (functionCalls * 3) + booleanOperators + comparisons;
    }

    public Integer visit(Literal expression) { return score(expression); }
    public Integer visit(VariableReference expression) { return score(expression); }
    public Integer visit(Addition expression) { return score(expression); }
    public Integer visit(Subtraction expression) { return score(expression); }
    public Integer visit(Multiplication expression) { return score(expression); }
    public Integer visit(Division expression) { return score(expression); }
    public Integer visit(Negation expression) { return score(expression); }
    public Integer visit(Modulo expression) { return score(expression); }
    public Integer visit(Exponentiation expression) { return score(expression); }
    public Integer visit(Equality expression) { return score(expression); }
    public Integer visit(Inequality expression) { return score(expression); }
    public Integer visit(LessThan expression) { return score(expression); }
    public Integer visit(GreaterThan expression) { return score(expression); }
    public Integer visit(LessThanOrEqual expression) { return score(expression); }
    public Integer visit(GreaterThanOrEqual expression) { return score(expression); }
    public Integer visit(Conjunction expression) { return score(expression); }
    public Integer visit(Disjunction expression) { return score(expression); }
    public Integer visit(LogicalNot expression) { return score(expression); }
    public Integer visit(Conditional expression) { return score(expression); }
    public Integer visit(FunctionCall expression) { return score(expression); }
}