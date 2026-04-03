package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class UnaryOperatorDepthHistogramBuilder implements Visitor<Map<Integer, Integer>> {
    UnaryOperatorDepthHistogramBuilder() {}

    private boolean active;
    private int depth;
    private Map<Integer, Integer> histogram;

    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, 0, histogram);
        return histogram;
    }
    private void collect(Expression expression, int depth, Map<Integer, Integer> histogram) {
        boolean previousActive = this.active;
        this.active = true;
        int previousDepth = this.depth;
        this.depth = depth;
        Map<Integer, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        expression.accept(this);
        this.histogram = previousHistogram;
        this.depth = previousDepth;
        this.active = previousActive;
    }

    public Map<Integer, Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public Map<Integer, Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public Map<Integer, Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, depth + 1, histogram); collect(expression.divisor, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } add(depth, histogram); collect(expression.operand, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, depth + 1, histogram); collect(expression.exponent, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, depth + 1, histogram); collect(expression.right, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } add(depth, histogram); collect(expression.operand, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, depth + 1, histogram); collect(expression.whenTrue, depth + 1, histogram); collect(expression.whenFalse, depth + 1, histogram); return null; }
    public Map<Integer, Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        collect(expression.callee, depth + 1, histogram);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, histogram);
        }
        return null;
    }


    private void add(int depth, Map<Integer, Integer> histogram) {
        histogram.put(depth, histogram.getOrDefault(depth, 0) + 1);
    }
}