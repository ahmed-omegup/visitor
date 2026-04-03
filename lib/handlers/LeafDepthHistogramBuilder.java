package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LeafDepthHistogramBuilder implements Visitor<Map<Integer, Integer>> {
    LeafDepthHistogramBuilder() {}

    private boolean active;
    private Map<Integer, Integer> histogram;
    private int depth;

    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram, 0);
        return histogram;
    }
    private void collect(Expression expression, Map<Integer, Integer> histogram, int depth) {
        boolean previousActive = this.active;
        this.active = true;
        Map<Integer, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        int previousDepth = this.depth;
        this.depth = depth;
        expression.accept(this);
        this.depth = previousDepth;
        this.histogram = previousHistogram;
        this.active = previousActive;
    }

    private void leaf() {
        histogram.merge(depth, 1, Integer::sum);
    }

    public Map<Integer, Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } leaf(); return null; }
    public Map<Integer, Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } leaf(); return null; }
    public Map<Integer, Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, histogram, depth + 1); collect(expression.divisor, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, histogram, depth + 1); collect(expression.exponent, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, histogram, depth + 1); collect(expression.right, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, histogram, depth + 1); collect(expression.whenTrue, histogram, depth + 1); collect(expression.whenFalse, histogram, depth + 1); return null; }
    public Map<Integer, Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); } collect(expression.callee, histogram, depth + 1); for (var argument : expression.arguments) { collect(argument, histogram, depth + 1); } return null; }

}