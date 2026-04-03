package lib.handlers;

import java.util.ArrayList;
import java.util.List;

import lib.expression.*;

public class LiteralCollector implements Visitor<List<String>> {
    LiteralCollector() {}

    private boolean active;
    private List<String> literals;

    public List<String> handle(Expression expression) {
        var literals = new ArrayList<String>();
        collect(expression, literals);
        return literals;
    }
    private void collect(Expression expression, List<String> literals) {
        boolean previousActive = this.active;
        this.active = true;
        List<String> previousLiterals = this.literals;
        this.literals = literals;
        expression.accept(this);
        this.literals = previousLiterals;
        this.active = previousActive;
    }

    public List<String> visit(Literal expression) {
            if (!active) { return handle(expression); } literals.add(expression.value); return null; }
    public List<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } return null; }
    public List<String> visit(Addition expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Division expression) {
            if (!active) { return handle(expression); } collect(expression.dividend, literals); collect(expression.divisor, literals); return null; }
    public List<String> visit(Negation expression) {
            if (!active) { return handle(expression); } collect(expression.operand, literals); return null; }
    public List<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } collect(expression.base, literals); collect(expression.exponent, literals); return null; }
    public List<String> visit(Equality expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } collect(expression.left, literals); collect(expression.right, literals); return null; }
    public List<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } collect(expression.operand, literals); return null; }
    public List<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } collect(expression.condition, literals); collect(expression.whenTrue, literals); collect(expression.whenFalse, literals); return null; }
    public List<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        collect(expression.callee, literals);
        for (var argument : expression.arguments) {
            collect(argument, literals);
        }
        return null;
    }

}