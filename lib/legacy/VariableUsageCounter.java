package lib.legacy;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class VariableUsageCounter extends AbstractExpressionFunction<Map<String, Integer>> {
    VariableUsageCounter() {}
    private Map<String, Integer> counts;

    public Map<String, Integer> apply(Expression expression) {
        var counts = new LinkedHashMap<String, Integer>();
        count(expression, counts);
        return counts;
    }
    private void count(Expression expression, Map<String, Integer> counts) {
        Map<String, Integer> previousCounts = this.counts;
        this.counts = counts;
        visitExpression(expression);
        this.counts = previousCounts;
    }

    public Map<String, Integer> visit(Literal expression) { return null; }
    public Map<String, Integer> visit(VariableReference expression) { counts.merge(expression.name, 1, Integer::sum); return null; }
    public Map<String, Integer> visit(Addition expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Subtraction expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Multiplication expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Division expression) { count(expression.dividend, counts); count(expression.divisor, counts); return null; }
    public Map<String, Integer> visit(Negation expression) { count(expression.operand, counts); return null; }
    public Map<String, Integer> visit(Modulo expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Exponentiation expression) { count(expression.base, counts); count(expression.exponent, counts); return null; }
    public Map<String, Integer> visit(Equality expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Inequality expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(LessThan expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(GreaterThan expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Conjunction expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Disjunction expression) { count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(LogicalNot expression) { count(expression.operand, counts); return null; }
    public Map<String, Integer> visit(Conditional expression) { count(expression.condition, counts); count(expression.whenTrue, counts); count(expression.whenFalse, counts); return null; }
    public Map<String, Integer> visit(FunctionCall expression) { count(expression.callee, counts);
        for (var argument : expression.arguments) {
            count(argument, counts);
        }
        return null;
    }

}