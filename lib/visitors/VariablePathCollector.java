package lib.visitors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lib.expression.*;

public class VariablePathCollector implements Visitor<Map<String, List<String>>> {
    VariablePathCollector() {}

    private boolean active;
    private String path;
    private Map<String, List<String>> paths;

    public Map<String, List<String>> handle(Expression expression) {
        var paths = new LinkedHashMap<String, List<String>>();
        collect(expression, "root", paths);
        return paths;
    }
    private void collect(Expression expression, String path, Map<String, List<String>> paths) {
        boolean previousActive = this.active;
        this.active = true;
        String previousPath = this.path;
        this.path = path;
        Map<String, List<String>> previousPaths = this.paths;
        this.paths = paths;
        expression.accept(this);
        this.paths = previousPaths;
        this.path = previousPath;
        this.active = previousActive;
    }

    public Map<String, List<String>> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public Map<String, List<String>> visit(VariableReference expression) {
            if (!active) { return handle(expression); } paths.computeIfAbsent(expression.name, ignored -> new ArrayList<>()).add(path); return null; }
    public Map<String, List<String>> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, path + ".dividend", paths); collect(expression.divisor, path + ".divisor", paths); return null; }
    public Map<String, List<String>> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, path + ".operand", paths); return null; }
    public Map<String, List<String>> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, path + ".base", paths); collect(expression.exponent, path + ".exponent", paths); return null; }
    public Map<String, List<String>> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, path + ".left", paths); collect(expression.right, path + ".right", paths); return null; }
    public Map<String, List<String>> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, path + ".operand", paths); return null; }
    public Map<String, List<String>> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, path + ".condition", paths); collect(expression.whenTrue, path + ".whenTrue", paths); collect(expression.whenFalse, path + ".whenFalse", paths); return null; }
    public Map<String, List<String>> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        collect(expression.callee, path + ".callee", paths);
        for (int index = 0; index < expression.arguments.length; index++) {
            collect(expression.arguments[index], path + ".arguments[" + index + "]", paths);
        }
        return null;
    }

}