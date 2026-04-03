package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class FunctionAritySequenceBuilder implements Visitor<List<Integer>> {
    FunctionAritySequenceBuilder() {}

    private boolean active;
    private List<Integer> arities;

    public List<Integer> handle(Expression expression) {
        var arities = new ArrayList<Integer>();
        collect(expression, arities);
        return arities;
    }
    private void collect(Expression expression, List<Integer> arities) {
        boolean previousActive = this.active;
        this.active = true;
        List<Integer> previousArities = this.arities;
        this.arities = arities;
        expression.accept(this);
        this.arities = previousArities;
        this.active = previousActive;
    }

    public List<Integer> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public List<Integer> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public List<Integer> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, arities); collect(expression.divisor, arities); return null; }
    public List<Integer> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, arities); return null; }
    public List<Integer> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, arities); collect(expression.exponent, arities); return null; }
    public List<Integer> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, arities); collect(expression.right, arities); return null; }
    public List<Integer> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, arities); return null; }
    public List<Integer> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, arities); collect(expression.whenTrue, arities); collect(expression.whenFalse, arities); return null; }
    public List<Integer> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        arities.add(expression.arguments.length);
        collect(expression.callee, arities);
        for (var argument : expression.arguments) {
            collect(argument, arities);
        }
        return null;
    }

}