package lib.visitors;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class OperatorSequenceCollector implements Visitor<List<String>> {
    OperatorSequenceCollector() {}

    private boolean active;
    private List<String> sequence;

    public List<String> handle(Expression expression) {
        var sequence = new ArrayList<String>();
        collect(expression, sequence);
        return sequence;
    }
    private void collect(Expression expression, List<String> sequence) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousSequence = this.sequence;
        this.sequence = sequence;
        expression.accept(this);
        this.sequence = previousSequence;
        this.active = previousActive;
    }

    public List<String> visit(Literal expression) {
            if (!active) { return handle(expression); } return null; }
    public List<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public List<String> visit(Addition expression) {
            if (!active) { return handle(expression); } sequence.add("Addition"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } sequence.add("Subtraction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } sequence.add("Multiplication"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Division expression) {
            if (!active) { return handle(expression); } sequence.add("Division"); collect(expression.dividend, sequence); collect(expression.divisor, sequence); return null; }
    public List<String> visit(Negation expression) {
            if (!active) { return handle(expression); } sequence.add("Negation"); collect(expression.operand, sequence); return null; }
    public List<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } sequence.add("Modulo"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } sequence.add("Exponentiation"); collect(expression.base, sequence); collect(expression.exponent, sequence); return null; }
    public List<String> visit(Equality expression) {
            if (!active) { return handle(expression); } sequence.add("Equality"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } sequence.add("Inequality"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } sequence.add("LessThan"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } sequence.add("GreaterThan"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } sequence.add("LessThanOrEqual"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } sequence.add("GreaterThanOrEqual"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } sequence.add("Conjunction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } sequence.add("Disjunction"); collect(expression.left, sequence); collect(expression.right, sequence); return null; }
    public List<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } sequence.add("LogicalNot"); collect(expression.operand, sequence); return null; }
    public List<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } sequence.add("Conditional"); collect(expression.condition, sequence); collect(expression.whenTrue, sequence); collect(expression.whenFalse, sequence); return null; }
    public List<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        sequence.add("FunctionCall");
        collect(expression.callee, sequence);
        for (var argument : expression.arguments) {
            collect(argument, sequence);
        }
        return null;
    }

}