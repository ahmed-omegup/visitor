package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class BranchingFactorHistogramBuilder extends AbstractExpressionFunction<Map<Integer, Integer>> {
    BranchingFactorHistogramBuilder() {}
    private Map<Integer, Integer> histogram;

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram);
        return histogram;
    }
    private void collect(Expression expression, Map<Integer, Integer> histogram) {
        Map<Integer, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        visitExpression(expression);
        this.histogram = previousHistogram;
    }

    public Map<Integer, Integer> visit(Literal expression) { add(0, histogram); return null; }
    public Map<Integer, Integer> visit(VariableReference expression) { add(0, histogram); return null; }
    public Map<Integer, Integer> visit(Addition expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Division expression) { add(2, histogram); collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
    public Map<Integer, Integer> visit(Negation expression) { add(1, histogram); collect(expression.operand, histogram); return null; }
    public Map<Integer, Integer> visit(Modulo expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) { add(2, histogram); collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
    public Map<Integer, Integer> visit(Equality expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Inequality expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LessThan expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) { add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) { add(1, histogram); collect(expression.operand, histogram); return null; }
    public Map<Integer, Integer> visit(Conditional expression) { add(3, histogram); collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
    public Map<Integer, Integer> visit(FunctionCall expression) { add(expression.arguments.length + 1, histogram);
        collect(expression.callee, histogram);
        for (var argument : expression.arguments) {
            collect(argument, histogram);
        }
        return null;
    }


    private void add(int branchingFactor, Map<Integer, Integer> histogram) {
        histogram.put(branchingFactor, histogram.getOrDefault(branchingFactor, 0) + 1);
    }
}