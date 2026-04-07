package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class BinaryOperatorDepthHistogramBuilder extends AbstractExpressionFunction<Map<Integer, Integer>> {
    BinaryOperatorDepthHistogramBuilder() {}
    private int depth;
    private Map<Integer, Integer> histogram;

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, 0, histogram);
        return histogram;
    }
    private void collect(Expression expression, int depth, Map<Integer, Integer> histogram) {
        int previousDepth = this.depth;
        this.depth = depth;
        Map<Integer, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        visitExpression(expression);
        this.histogram = previousHistogram;
        this.depth = previousDepth;
    }

    public Map<Integer, Integer> visit(Literal expression) { return null; }
    public Map<Integer, Integer> visit(VariableReference expression) { return null; }
    public Map<Integer, Integer> visit(Addition expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Division expression) { add(depth, histogram); collect(expression.dividend, depth + 1, histogram); collect(expression.divisor, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Negation expression) { collect(expression.operand, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Modulo expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) { add(depth, histogram); collect(expression.base, depth + 1, histogram); collect(expression.exponent, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Equality expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Inequality expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(LessThan expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) { add(depth, histogram); collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) { collect(expression.operand, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Conditional expression) { collect(expression.condition, depth + 1, histogram); collect(expression.whenTrue, depth + 1, histogram); collect(expression.whenFalse, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(FunctionCall expression) { collect(expression.callee, depth + 1, histogram);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, histogram);
        }
        return null;
    }


    private void add(int depth, Map<Integer, Integer> histogram) {
        histogram.put(depth, histogram.getOrDefault(depth, 0) + 1);
    }
}