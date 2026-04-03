package lib.handlers;

import java.util.LinkedHashSet;
import java.util.Set;

import lib.expression.*;

public class NodeTypeCollector implements Visitor<Set<String>> {
    NodeTypeCollector() {}

    private boolean active;
    private Set<String> types;

    public Set<String> handle(Expression expression) {
        var types = new LinkedHashSet<String>();
        collect(expression, types);
        return types;
    }
    private void collect(Expression expression, Set<String> types) {
        boolean previousActive = this.active;
        this.active = true;
        Set<String> previousTypes = this.types;
        this.types = types;
        expression.accept(this);
        this.types = previousTypes;
        this.active = previousActive;
    }

    public Set<String> visit(Literal expression) {
            if (!active) { return handle(expression); } types.add("Literal"); return null; }
    public Set<String> visit(VariableReference expression) {
            if (!active) { return handle(expression); } types.add("VariableReference"); return null; }
    public Set<String> visit(Addition expression) {
            if (!active) { return handle(expression); } types.add("Addition"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Subtraction expression) {
            if (!active) { return handle(expression); } types.add("Subtraction"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Multiplication expression) {
            if (!active) { return handle(expression); } types.add("Multiplication"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Division expression) {
            if (!active) { return handle(expression); } types.add("Division"); collect(expression.dividend, types); collect(expression.divisor, types); return null; }
    public Set<String> visit(Negation expression) {
            if (!active) { return handle(expression); } types.add("Negation"); collect(expression.operand, types); return null; }
    public Set<String> visit(Modulo expression) {
            if (!active) { return handle(expression); } types.add("Modulo"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Exponentiation expression) {
            if (!active) { return handle(expression); } types.add("Exponentiation"); collect(expression.base, types); collect(expression.exponent, types); return null; }
    public Set<String> visit(Equality expression) {
            if (!active) { return handle(expression); } types.add("Equality"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Inequality expression) {
            if (!active) { return handle(expression); } types.add("Inequality"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(LessThan expression) {
            if (!active) { return handle(expression); } types.add("LessThan"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(GreaterThan expression) {
            if (!active) { return handle(expression); } types.add("GreaterThan"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(LessThanOrEqual expression) {
            if (!active) { return handle(expression); } types.add("LessThanOrEqual"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(GreaterThanOrEqual expression) {
            if (!active) { return handle(expression); } types.add("GreaterThanOrEqual"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Conjunction expression) {
            if (!active) { return handle(expression); } types.add("Conjunction"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(Disjunction expression) {
            if (!active) { return handle(expression); } types.add("Disjunction"); collect(expression.left, types); collect(expression.right, types); return null; }
    public Set<String> visit(LogicalNot expression) {
            if (!active) { return handle(expression); } types.add("LogicalNot"); collect(expression.operand, types); return null; }
    public Set<String> visit(Conditional expression) {
            if (!active) { return handle(expression); } types.add("Conditional"); collect(expression.condition, types); collect(expression.whenTrue, types); collect(expression.whenFalse, types); return null; }
    public Set<String> visit(FunctionCall expression) {
            if (!active) { return handle(expression); }
        types.add("FunctionCall");
        collect(expression.callee, types);
        for (var argument : expression.arguments) {
            collect(argument, types);
        }
        return null;
    }

}