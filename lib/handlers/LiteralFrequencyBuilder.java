package lib.handlers;

import java.util.LinkedHashMap;
import java.util.Map;

import lib.expression.*;

public class LiteralFrequencyBuilder implements Visitor<Map<String, Integer>> {
    LiteralFrequencyBuilder() {}

    private boolean active;
    private Map<String, Integer> frequencies;

    public Map<String, Integer> handle(Expression expression) {
        var frequencies = new LinkedHashMap<String, Integer>();
        collect(expression, frequencies);
        return frequencies;
    }
    private void collect(Expression expression, Map<String, Integer> frequencies) {
        boolean previousActive = this.active;
        this.active = true;
        Map<String, Integer> previousFrequencies = this.frequencies;
        this.frequencies = frequencies;
        expression.accept(this);
        this.frequencies = previousFrequencies;
        this.active = previousActive;
    }

    public Map<String, Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } frequencies.merge(expression.value, 1, Integer::sum); return null; }
    public Map<String, Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public Map<String, Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, frequencies); collect(expression.divisor, frequencies); return null; }
    public Map<String, Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, frequencies); return null; }
    public Map<String, Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, frequencies); collect(expression.exponent, frequencies); return null; }
    public Map<String, Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, frequencies); collect(expression.right, frequencies); return null; }
    public Map<String, Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, frequencies); return null; }
    public Map<String, Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, frequencies); collect(expression.whenTrue, frequencies); collect(expression.whenFalse, frequencies); return null; }
    public Map<String, Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        collect(expression.callee, frequencies);
        for (var argument : expression.arguments) {
            collect(argument, frequencies);
        }
        return null;
    }

}