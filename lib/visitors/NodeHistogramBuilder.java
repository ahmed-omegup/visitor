package lib.visitors;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class NodeHistogramBuilder implements Visitor<Map<String, Integer>> {
    NodeHistogramBuilder() {}

    private boolean active;
    private Map<String, Integer> histogram;

    public Map<String, Integer> handle(Expression expression) {
        var histogram = new LinkedHashMap<String, Integer>();
        populate(expression, histogram);
        return histogram;
    }
    private void populate(Expression expression, Map<String, Integer> histogram) {
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
            if (!active) { return handle(expression); } hit("Literal"); return null; }
    public Map<String, Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } hit("VariableReference"); return null; }
    public Map<String, Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } hit("Addition"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } hit("Subtraction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } hit("Multiplication"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Division expression) {
            if (!active) { return handle(expression); } hit("Division"); populate(expression.dividend, histogram); populate(expression.divisor, histogram); return null; }
    public Map<String, Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } hit("Negation"); populate(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } hit("Modulo"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } hit("Exponentiation"); populate(expression.base, histogram); populate(expression.exponent, histogram); return null; }
    public Map<String, Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } hit("Equality"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } hit("Inequality"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } hit("LessThan"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } hit("GreaterThan"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } hit("LessThanOrEqual"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } hit("GreaterThanOrEqual"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } hit("Conjunction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } hit("Disjunction"); populate(expression.left, histogram); populate(expression.right, histogram); return null; }
    public Map<String, Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } hit("LogicalNot"); populate(expression.operand, histogram); return null; }
    public Map<String, Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } hit("Conditional"); populate(expression.condition, histogram); populate(expression.whenTrue, histogram); populate(expression.whenFalse, histogram); return null; }
    public Map<String, Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        hit("FunctionCall");
        populate(expression.callee, histogram);
        for (var argument : expression.arguments) {
            populate(argument, histogram);
        }
        return null;
    }

}