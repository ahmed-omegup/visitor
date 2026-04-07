package lib.legacy;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LevelWidthHistogramBuilder extends AbstractExpressionFunction<Map<Integer, Integer>> {
    LevelWidthHistogramBuilder() {}
    private ArrayDeque<Expression> queue;
    private ArrayDeque<Integer> depths;
    private Map<Integer, Integer> histogram;
    private int currentDepth;

    public Map<Integer, Integer> apply(Expression expression) {
        ArrayDeque<Expression> previousQueue = this.queue;
        ArrayDeque<Integer> previousDepths = this.depths;
        Map<Integer, Integer> previousHistogram = this.histogram;
        int previousCurrentDepth = this.currentDepth;
        this.queue = new ArrayDeque<>();
        this.depths = new ArrayDeque<>();
        this.histogram = new LinkedHashMap<>();
        this.queue.add(expression);
        this.depths.add(0);

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();
            currentDepth = depths.removeFirst();
            histogram.merge(currentDepth, 1, Integer::sum);
            visitExpression(current);
        }

        Map<Integer, Integer> result = histogram;
        this.currentDepth = previousCurrentDepth;
        this.histogram = previousHistogram;
        this.depths = previousDepths;
        this.queue = previousQueue;
        return result;
    }

    private void push(Expression... children) {
        for (var child : children) {
            queue.addLast(child);
            depths.addLast(currentDepth + 1);
        }
    }

    public Map<Integer, Integer> visit(Literal expression) { return null; }
    public Map<Integer, Integer> visit(VariableReference expression) { return null; }
    public Map<Integer, Integer> visit(Addition expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Division expression) { push(expression.dividend, expression.divisor); return null; }
    public Map<Integer, Integer> visit(Negation expression) { push(expression.operand); return null; }
    public Map<Integer, Integer> visit(Modulo expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) { push(expression.base, expression.exponent); return null; }
    public Map<Integer, Integer> visit(Equality expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Inequality expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(LessThan expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) { push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) { push(expression.operand); return null; }
    public Map<Integer, Integer> visit(Conditional expression) { push(expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public Map<Integer, Integer> visit(FunctionCall expression) { queue.addLast(expression.callee);
        depths.addLast(currentDepth + 1);
        for (var argument : expression.arguments) {
            queue.addLast(argument);
            depths.addLast(currentDepth + 1);
        }
        return null;
    }
}