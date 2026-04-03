package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class BranchingFactorHistogramBuilder implements Visitor<Map<Integer, Integer>> {
    BranchingFactorHistogramBuilder() {}

    private boolean active;
    private Map<Integer, Integer> histogram;

    public Map<Integer, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<Integer, Integer>();
        collect(expression, histogram);
        return histogram;
    }
    private void collect(Expression expression, Map<Integer, Integer> histogram) {
        boolean previousActive = this.active;
        this.active = true;
        Map<Integer, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        expression.accept(this);
        this.histogram = previousHistogram;
        this.active = previousActive;
    }

    public Map<Integer, Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } add(0, histogram); return null; }
    public Map<Integer, Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } add(0, histogram); return null; }
    public Map<Integer, Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Division expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
    public Map<Integer, Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } add(1, histogram); collect(expression.operand, histogram); return null; }
    public Map<Integer, Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
    public Map<Integer, Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } add(2, histogram); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<Integer, Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } add(1, histogram); collect(expression.operand, histogram); return null; }
    public Map<Integer, Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } add(3, histogram); collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
    public Map<Integer, Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        add(expression.arguments.length + 1, histogram);
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