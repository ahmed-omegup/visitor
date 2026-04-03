package lib.visitors;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class DistinctLeafLabelCollector implements Visitor<Set<String>> {
    DistinctLeafLabelCollector() {}

    private boolean active;
    private Set<String> labels;

    public Set<String> handle(Expression expression) {
        var labels = new LinkedHashSet<String>();
        collect(expression, labels);
        return labels;
    }
    private void collect(Expression expression, Set<String> labels) {
        boolean previousActive = this.active;
        this.active = true;
        Set<String> previousLabels = this.labels;
        this.labels = labels;
        expression.accept(this);
        this.labels = previousLabels;
        this.active = previousActive;
    }

    public Set<String> visit(Literal expression) {
            if (!active) { return handle(expression); } labels.add("literal:" + expression.value); return null; }
    public Set<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } labels.add("variable:" + expression.name); return null; }
    public Set<String> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
    public Set<String> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, labels); return null; }
    public Set<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, labels); collect(expression.exponent, labels); return null; }
    public Set<String> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public Set<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, labels); return null; }
    public Set<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }
    public Set<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        return null;
    }

}