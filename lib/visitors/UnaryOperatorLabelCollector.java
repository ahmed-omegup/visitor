package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class UnaryOperatorLabelCollector implements Visitor<List<String>> {
    UnaryOperatorLabelCollector() {}

    private boolean active;
    private List<String> labels;

    public List<String> handle(Expression expression) {
        var labels = new ArrayList<String>();
        collect(expression, labels);
        return labels;
    }
    private void collect(Expression expression, List<String> labels) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousLabels = this.labels;
        this.labels = labels;
        expression.accept(this);
        this.labels = previousLabels;
        this.active = previousActive;
    }

    public List<String> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public List<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public List<String> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, labels); collect(expression.divisor, labels); return null; }
    public List<String> visit(Negation expression) {
            if (!active) { return handle(expression); } labels.add("Negation"); collect(expression.operand, labels); return null; }
    public List<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, labels); collect(expression.exponent, labels); return null; }
    public List<String> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, labels); collect(expression.right, labels); return null; }
    public List<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } labels.add("LogicalNot"); collect(expression.operand, labels); return null; }
    public List<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, labels); collect(expression.whenTrue, labels); collect(expression.whenFalse, labels); return null; }
    public List<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        collect(expression.callee, labels);
        for (var argument : expression.arguments) {
            collect(argument, labels);
        }
        return null;
    }

}