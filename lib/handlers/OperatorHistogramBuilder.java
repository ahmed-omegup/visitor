package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class OperatorHistogramBuilder implements Visitor<Map<String, Integer>> {
    OperatorHistogramBuilder() {}

    private boolean active;
    private Map<String, Integer> histogram;

    public Map<String, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<String, Integer>();
        collect(expression, histogram);
        return histogram;
    }
    private void collect(Expression expression, Map<String, Integer> histogram) {
        boolean previousActive = this.active;
        this.active = true;
        Map<String, Integer> previousHistogram = this.histogram;
        this.histogram = histogram;
        expression.accept(this);
        this.histogram = previousHistogram;
        this.active = previousActive;
    }

    private void hit(String type) {
        histogram.merge(type, 1, Integer::sum);
    }

    public Map<String, Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public Map<String, Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public Map<String, Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } hit("Addition"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } hit("Subtraction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } hit("Multiplication"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Division expression) {
            if (!active) { return handle(expression); } hit("Division"); collect(expression.dividend, histogram); collect(expression.divisor, histogram); return null; }
    public Map<String, Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } hit("Negation"); collect(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } hit("Modulo"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } hit("Exponentiation"); collect(expression.base, histogram); collect(expression.exponent, histogram); return null; }
    public Map<String, Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } hit("Equality"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } hit("Inequality"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } hit("LessThan"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } hit("GreaterThan"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } hit("LessThanOrEqual"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } hit("GreaterThanOrEqual"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } hit("Conjunction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } hit("Disjunction"); collect(expression.left, histogram); collect(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } hit("LogicalNot"); collect(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } hit("Conditional"); collect(expression.condition, histogram); collect(expression.whenTrue, histogram); collect(expression.whenFalse, histogram); return null; }
    public Map<String, Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        hit("FunctionCall");
        collect(expression.callee, histogram);
        for (var argument : expression.arguments) {
            collect(argument, histogram);
        }
        return null;
    }

}