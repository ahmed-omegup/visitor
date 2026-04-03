package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class VariableUsageCounter implements Visitor<Map<String, Integer>> {
    VariableUsageCounter() {}

    private boolean active;
    private Map<String, Integer> counts;

    public Map<String, Integer> handle(Expression expression) {
        var counts = new LinkedHashMap<String, Integer>();
        count(expression, counts);
        return counts;
    }
    private void count(Expression expression, Map<String, Integer> counts) {
        boolean previousActive = this.active;
        this.active = true;
        Map<String, Integer> previousCounts = this.counts;
        this.counts = counts;
        expression.accept(this);
        this.counts = previousCounts;
        this.active = previousActive;
    }

    public Map<String, Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public Map<String, Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } counts.merge(expression.name, 1, Integer::sum); return null; }
    public Map<String, Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Division expression) {
            if (!active) { return handle(expression); } count(expression.dividend, counts); count(expression.divisor, counts); return null; }
    public Map<String, Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } count(expression.operand, counts); return null; }
    public Map<String, Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } count(expression.base, counts); count(expression.exponent, counts); return null; }
    public Map<String, Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } count(expression.left, counts); count(expression.right, counts); return null; }
    public Map<String, Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } count(expression.operand, counts); return null; }
    public Map<String, Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } count(expression.condition, counts); count(expression.whenTrue, counts); count(expression.whenFalse, counts); return null; }
    public Map<String, Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        count(expression.callee, counts);
        for (var argument : expression.arguments) {
            count(argument, counts);
        }
        return null;
    }

}