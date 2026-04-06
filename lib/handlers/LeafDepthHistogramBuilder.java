package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LeafDepthHistogramBuilder extends AbstractExpressionFunction<Map<Integer, Integer>> {
    LeafDepthHistogramBuilder() {}
    private Map<Integer, Integer> histogram;
    private int depth;

    public Map<Integer, Integer> apply(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram, 0);
        return histogram;
    }
    private void collect(Expression expression, Map<Integer, Integer> histogram, int depth) {
        Map<Integer, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        int previousDepth = this.depth;
        this.depth = depth;
        visitExpression(expression);
        this.depth = previousDepth;
        this.histogram = previousHistogram;
    }

    private void leaf() {
        histogram.merge(depth, 1, Integer::sum);
    }

    public Map<Integer, Integer> visit(Literal expression) { leaf(); return null; }
    public Map<Integer, Integer> visit(VariableReference expression) { leaf(); return null; }
    public Map<Integer, Integer> visit(Addition expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Division expression) { collect(expression.dividend, histogram, depth + 1); collect(expression.divisor, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Negation expression) { collect(expression.operand, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Modulo expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) { collect(expression.base, histogram, depth + 1); collect(expression.exponent, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Equality expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Inequality expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(LessThan expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) { collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) { collect(expression.operand, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Conditional expression) { collect(expression.condition, histogram, depth + 1); collect(expression.whenTrue, histogram, depth + 1); collect(expression.whenFalse, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(FunctionCall expression) { collect(expression.callee, histogram, depth + 1); for (var argument : expression.arguments) { collect(argument, histogram, depth + 1); } return null; }

}