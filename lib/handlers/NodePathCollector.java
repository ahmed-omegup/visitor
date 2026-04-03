package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class NodePathCollector implements Visitor<List<String>> {
    NodePathCollector() {}

    private boolean active;
    private List<String> paths;
    private String path;

    public List<String> handle(Expression expression) {
        var paths = new ArrayList<String>();
        collect(expression, paths, "0");
        return paths;
    }
    private void collect(Expression expression, List<String> paths, String path) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousPaths = this.paths;
        this.paths = paths;
        String previousPath = this.path;
        this.path = path;
        expression.accept(this);
        this.path = previousPath;
        this.paths = previousPaths;
        this.active = previousActive;
    }

    private void add(String type) {
        paths.add(path + ':' + type);
    }

    public List<String> visit(Literal expression) {
            if (!active) { return handle(expression); } add("Literal"); return null; }
    public List<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } add("VariableReference"); return null; }
    public List<String> visit(Addition expression) {
            if (!active) { return handle(expression); } add("Addition"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } add("Subtraction"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } add("Multiplication"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(Division expression) {
            if (!active) { return handle(expression); } add("Division"); collect(expression.dividend, paths, path + ".0"); collect(expression.divisor, paths, path + ".1"); return null; }
    public List<String> visit(Negation expression) {
            if (!active) { return handle(expression); } add("Negation"); collect(expression.operand, paths, path + ".0"); return null; }
    public List<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } add("Modulo"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } add("Exponentiation"); collect(expression.base, paths, path + ".0"); collect(expression.exponent, paths, path + ".1"); return null; }
    public List<String> visit(Equality expression) {
            if (!active) { return handle(expression); } add("Equality"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } add("Inequality"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } add("LessThan"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } add("GreaterThan"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } add("LessThanOrEqual"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } add("GreaterThanOrEqual"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } add("Conjunction"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } add("Disjunction"); collect(expression.left, paths, path + ".0"); collect(expression.right, paths, path + ".1"); return null; }
    public List<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } add("LogicalNot"); collect(expression.operand, paths, path + ".0"); return null; }
    public List<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } add("Conditional"); collect(expression.condition, paths, path + ".0"); collect(expression.whenTrue, paths, path + ".1"); collect(expression.whenFalse, paths, path + ".2"); return null; }
    public List<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); } add("FunctionCall"); collect(expression.callee, paths, path + ".0"); for (int index = 0; index < expression.arguments.length; index++) { collect(expression.arguments[index], paths, path + "." + (index + 1)); } return null; }

}