package lib.handlers;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LevelWidthHistogramBuilder implements Visitor<Map<Integer, Integer>> {
    LevelWidthHistogramBuilder() {}

    private boolean active;
    private ArrayDeque<Expression> queue;
    private ArrayDeque<Integer> depths;
    private Map<Integer, Integer> histogram;
    private int currentDepth;

    public Map<Integer, Integer> handle(Expression expression) {
        boolean previousActive = this.active;
        ArrayDeque<Expression> previousQueue = this.queue;
        ArrayDeque<Integer> previousDepths = this.depths;
        Map<Integer, Integer> previousHistogram = this.histogram;
        int previousCurrentDepth = this.currentDepth;

        this.active = true;
        this.queue = new ArrayDeque<>();
        this.depths = new ArrayDeque<>();
        this.histogram = new LinkedHashMap<>();
        this.queue.add(expression);
        this.depths.add(0);

        while (!queue.isEmpty()) {
            var current = queue.removeFirst();
            currentDepth = depths.removeFirst();
            histogram.merge(currentDepth, 1, Integer::sum);
            current.accept(this);
        }

        Map<Integer, Integer> result = histogram;
        this.currentDepth = previousCurrentDepth;
        this.histogram = previousHistogram;
        this.depths = previousDepths;
        this.queue = previousQueue;
        this.active = previousActive;
        return result;
    }

    private void push(Expression... children) {
        for (var child : children) {
            queue.addLast(child);
            depths.addLast(currentDepth + 1);
        }
    }

    public Map<Integer, Integer> visit(Literal expression) { return active ? null : handle(expression); }
    public Map<Integer, Integer> visit(VariableReference expression) { return active ? null : handle(expression); }
    public Map<Integer, Integer> visit(Addition expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Division expression) { if (!active) { return handle(expression); } push(expression.dividend, expression.divisor); return null; }
    public Map<Integer, Integer> visit(Negation expression) { if (!active) { return handle(expression); } push(expression.operand); return null; }
    public Map<Integer, Integer> visit(Modulo expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) { if (!active) { return handle(expression); } push(expression.base, expression.exponent); return null; }
    public Map<Integer, Integer> visit(Equality expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Inequality expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(LessThan expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) { if (!active) { return handle(expression); } push(expression.left, expression.right); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) { if (!active) { return handle(expression); } push(expression.operand); return null; }
    public Map<Integer, Integer> visit(Conditional expression) { if (!active) { return handle(expression); } push(expression.condition, expression.whenTrue, expression.whenFalse); return null; }

    public Map<Integer, Integer> visit(FunctionCall expression) {
        if (!active) {
            return handle(expression);
        }
        queue.addLast(expression.callee);
        depths.addLast(currentDepth + 1);
        for (var argument : expression.arguments) {
            queue.addLast(argument);
            depths.addLast(currentDepth + 1);
        }
        return null;
    }
}