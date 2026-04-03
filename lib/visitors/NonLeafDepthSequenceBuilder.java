package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class NonLeafDepthSequenceBuilder implements Visitor<List<String>> {
    NonLeafDepthSequenceBuilder() {}

    private boolean active;
    private int depth;
    private List<String> sequence;

    public List<String> handle(Expression expression) {
        var sequence = new ArrayList<String>();
        collect(expression, 0, sequence);
        return sequence;
    }
    private void collect(Expression expression, int depth, List<String> sequence) {
        boolean previousActive = this.active;
        this.active = true;
        int previousDepth = this.depth;
        this.depth = depth;
        List<String> previousSequence = this.sequence;
        this.sequence = sequence;
        expression.accept(this);
        this.sequence = previousSequence;
        this.depth = previousDepth;
        this.active = previousActive;
    }

    public List<String> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public List<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public List<String> visit(Addition expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Addition"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Subtraction"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Multiplication"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(Division expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Division"); collect(expression.dividend, depth + 1, sequence); collect(expression.divisor, depth + 1, sequence); return null; }
    public List<String> visit(Negation expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Negation"); collect(expression.operand, depth + 1, sequence); return null; }
    public List<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Modulo"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Exponentiation"); collect(expression.base, depth + 1, sequence); collect(expression.exponent, depth + 1, sequence); return null; }
    public List<String> visit(Equality expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Equality"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Inequality"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":LessThan"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":GreaterThan"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":LessThanOrEqual"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":GreaterThanOrEqual"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Conjunction"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Disjunction"); collect(expression.left, depth + 1, sequence); collect(expression.right, depth + 1, sequence); return null; }
    public List<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":LogicalNot"); collect(expression.operand, depth + 1, sequence); return null; }
    public List<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } sequence.add(depth + ":Conditional"); collect(expression.condition, depth + 1, sequence); collect(expression.whenTrue, depth + 1, sequence); collect(expression.whenFalse, depth + 1, sequence); return null; }
    public List<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        sequence.add(depth + ":FunctionCall");
        collect(expression.callee, depth + 1, sequence);
        for (var argument : expression.arguments) {
            collect(argument, depth + 1, sequence);
        }
        return null;
    }

}