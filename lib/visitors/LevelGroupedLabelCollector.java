package lib.visitors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lib.expression.*;

public class LevelGroupedLabelCollector implements Visitor<Map<Integer, List<String>>> {
    LevelGroupedLabelCollector() {}

    private boolean active;
    private int depth;
    private Map<Integer, List<String>> grouped;

    public Map<Integer, List<String>> handle(Expression expression) {
        var grouped = new LinkedHashMap<Integer, List<String>>();
        collect(expression, 0, grouped);
        return grouped;
    }
    private void collect(Expression expression, int depth, Map<Integer, List<String>> grouped) {
        boolean previousActive = this.active;
        this.active = true;
        int previousDepth = this.depth;
        this.depth = depth;
        Map<Integer, List<String>> previousGrouped = this.grouped;
        this.grouped = grouped;
        expression.accept(this);
        this.grouped = previousGrouped;
        this.depth = previousDepth;
        this.active = previousActive;
    }

    public Map<Integer, List<String>> visit(Literal expression) {
            if (!active) { return handle(expression); } add(depth, "Literal", grouped); return null; }
    public Map<Integer, List<String>> visit(VariableReference expression) {
            if (!active) { return handle(expression); } add(depth, "VariableReference", grouped); return null; }
    public Map<Integer, List<String>> visit(Addition expression) {
            if (!active) { return handle(expression); } add(depth, "Addition", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Subtraction expression) {
            if (!active) { return handle(expression); } add(depth, "Subtraction", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Multiplication expression) {
            if (!active) { return handle(expression); } add(depth, "Multiplication", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Division expression) {
            if (!active) { return handle(expression); } add(depth, "Division", grouped); collect(expression.dividend, depth + 1, grouped); collect(expression.divisor, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Negation expression) {
            if (!active) { return handle(expression); } add(depth, "Negation", grouped); collect(expression.operand, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Modulo expression) {
            if (!active) { return handle(expression); } add(depth, "Modulo", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } add(depth, "Exponentiation", grouped); collect(expression.base, depth + 1, grouped); collect(expression.exponent, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Equality expression) {
            if (!active) { return handle(expression); } add(depth, "Equality", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Inequality expression) {
            if (!active) { return handle(expression); } add(depth, "Inequality", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(LessThan expression) {
            if (!active) { return handle(expression); } add(depth, "LessThan", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } add(depth, "GreaterThan", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } add(depth, "LessThanOrEqual", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } add(depth, "GreaterThanOrEqual", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Conjunction expression) {
            if (!active) { return handle(expression); } add(depth, "Conjunction", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Disjunction expression) {
            if (!active) { return handle(expression); } add(depth, "Disjunction", grouped); collect(expression.left, depth + 1, grouped); collect(expression.right, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } add(depth, "LogicalNot", grouped); collect(expression.operand, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(Conditional expression) {
            if (!active) { return handle(expression); } add(depth, "Conditional", grouped); collect(expression.condition, depth + 1, grouped); collect(expression.whenTrue, depth + 1, grouped); collect(expression.whenFalse, depth + 1, grouped); return null; }
    public Map<Integer, List<String>> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        add(depth, "FunctionCall", grouped);
        collect(expression.callee, depth + 1, grouped);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, grouped);
        }
        return null;
    }


    private void add(int depth, String label, Map<Integer, List<String>> grouped) {
        grouped.computeIfAbsent(depth, ignored -> new ArrayList<>()).add(label);
    }
}